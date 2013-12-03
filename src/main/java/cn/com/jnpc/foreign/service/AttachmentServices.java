package cn.com.jnpc.foreign.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jnpc.ems.dto.User;
import cn.com.jnpc.foreign.dao.attachmentDao;
import cn.com.jnpc.foreign.dao.fiBlobDao;
import cn.com.jnpc.foreign.po.FiAttachment;
import cn.com.jnpc.foreign.po.FiBlob;
import cn.com.jnpc.foreign.utils.Untils;

@Transactional
@Service(value = "AttachmentServices")
@Component
public class AttachmentServices {
	private static Logger log = Logger.getLogger(AttachmentServices.class);
	String blob_id = null;

	FiBlob blob;

	fiBlobDao blobdao;

	@Resource(name = "fiBlobDao")
	private void setBlobdao(fiBlobDao blobdao) {
		this.blobdao = blobdao;
	}

	FiAttachment attachment;

	attachmentDao attachmentdao;

	@Resource(name = "attachmentDao")
	private void setAttachmentdao(attachmentDao attachmentdao) {
		this.attachmentdao = attachmentdao;
	}

	String b_id;

	@Transactional(propagation = Propagation.REQUIRED)
	public FiAttachment InsertReturObject(MultipartFile blob_v, User user,
			int type) {
		try {
			FiBlob blobA = null;
			FiAttachment attachmentB = null;
			String path = null;
			if (blob_v.getSize() > 0) {
				blob = new FiBlob();
				blob.setFileV(blob_v.getBytes());
				blobA = blobdao.InsertReturnObject("insert", blob);
				path = Untils.getUrl(blob_v);
			}
			String name = blob_v.getOriginalFilename();
			attachment = new FiAttachment();
			attachment.setOldName(name);// 文件名称
			if (Untils.NotNull(path)) {
				attachment.setUrl(path);
			}
			attachment.setType(Integer.parseInt(type + ""));// 类型 是PP 还是 RP之类
			if (blobA != null) {
				if (Untils.NotNull(blobA.getId() + "")) {
					attachment.setFileId(blobA.getId() + "");// 对应的附件的存储
				}
			}

			attachment.setStatus(Integer.parseInt("0"));// 是否删除 1 删除 0 未删除
			attachment.setCreateTime(new Date());// 创建时间
			if (user != null && Untils.NotNull(user.getAccount())) {
				attachment.setCreateUser(user.getAccount());
			}
			attachment.setIsDefer(Integer.parseInt("0"));// 是否属于延期
			attachment.setParentType("1");// 父类ID 这里应该填写树状的ID
			attachmentB = attachmentdao
					.InsertReturnObject("insert", attachment);
			if (blobA != null && attachmentB != null) {
				if (Untils.NotNull(attachmentB.getId())) {
					blobA.setParentId(attachmentB.getId() + "");
					blobdao.Updata("updateByPrimaryKey", blobA);
				}
			}
			return attachment;
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info(e.toString());
			log.info(e.getCause());
			return null;
		} finally {
			return attachment;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public FiAttachment UpdataReturnObject(MultipartFile blob_v, User user,
			String id, int type) {
		try {
			FiBlob blobA = null;
			FiAttachment attachmentB = null;
			String path = null;
			if (Untils.NotNull(id)) {
				attachment = attachmentdao.SelectByPrimaryKey(
						"selectByPrimaryKey", id);
			} else {
				attachment = new FiAttachment();
			}
			if (blob_v.getSize() > 0) {
				if (Untils.NotNull(attachment.getFileId())) {
					blob = blobdao.SelectByPrimaryKey("selectByPrimaryKey",
							attachment.getFileId());
					blob.setFileV(blob_v.getBytes());
					blobA = blobdao.UpdataByPrimaryKey(
							"updateByPrimaryKeyWithBLOBs", blob);
				} else {
					blob = new FiBlob();
					blob.setFileV(blob_v.getBytes());
					blobA = blobdao.InsertReturnObject("insert", blob);
					if (blobA != null) {
						if (Untils.NotNull(blobA.getId())) {
							attachment.setFileId(blobA.getId() + "");// 对应的附件的存储
						}
					}
				}
				path = Untils.getUrl(blob_v);
				String name = blob_v.getOriginalFilename();
				attachment.setOldName(name);// 文件名称//类型 是PP 还是 RP之类
				if (Untils.NotNull(path)) {
					attachment.setUrl(path);
				}
				attachment.setStatus(Integer.parseInt("0"));// 是否删除 1 删除 0 未删除
				attachment.setType(type);
				attachment.setEditTime(new Date());// 创建时间
				if (user != null && Untils.NotNull(user.getAccount())) {
					attachment.setEditUser(user.getAccount());
				}
				attachment.setIsDefer(Integer.parseInt("0"));// 是否属于延期
				attachment.setParentType("1");// 父类ID 这里应该填写树状的ID
			}
			if (Untils.NotNull(attachment.getId())) {
				attachmentB = attachmentdao.UpdataReturnObject(
						"updateByPrimaryKey", attachment);
			} else {
				attachmentB = attachmentdao.InsertReturnObject("insert",
						attachment);
			}
			// System.out.println("attachment.getId(): "+attachment.getId());
			if (blobA != null && attachmentB != null) {
				if (Untils.NotNull(attachmentB.getId())) {
					blobA.setParentId(attachmentB.getId() + "");
					blobdao.Updata("updateByPrimaryKey", blobA);
				}
			}
			// System.out.println(blob);
			attachment=attachmentB;
			return attachment;
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info(e.toString());
			log.info(e.getCause());
			// System.out.println(e.toString()+" /ok/ "+e.getCause());
			return null;
		} finally {
			return attachment;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void UpdataObject(FiAttachment attachment) {
		attachmentdao.Updata("updateByPrimaryKey", attachment);
	}

	public FiAttachment QueryById(String id) {
		FiAttachment attach = attachmentdao.SelectByPrimaryKey(
				"selectByPrimaryKey", id);

		String path = attach.getUrl();
		path = path.replaceAll("\\\\", "/");
		String syspath = Untils.getfilepath();
		String now_path = Untils.checkDisk(path, syspath);
		if (!syspath.equals("/" + now_path.replaceAll("\\\\", "/"))) {
			return attach;
		} else {
			if (Untils.NotNull(attach.getFileId())) {
				FiBlob blob = blobdao.SelectByPrimaryKey("selectByPrimaryKey",
						attach.getFileId());
				String url = Untils.getUrl(blob, path, (Untils.NotNull(attach.getOldName())?attach.getOldName():attach.getUrl()));
				if (Untils.NotNull(url)) {
					attach.setUrl(url);
					UpdataObject(attach);
				}
			}
			return attach;

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void DeleteByID(String id) {
		attachmentdao.DeleteByPrimaryKey("deleteByPrimaryKey", id);
	}
}
