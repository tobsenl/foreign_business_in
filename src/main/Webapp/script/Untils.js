function split(val) {
	return val.split(/,\s*/);
}
function trim(str){ //删除左右两端的空格
     return str.replace(/(^\s*)|(\s*$)/g, "");
}
function getmatch(obj,val){
	for(var i=0;i<obj.length;i++){
		val=trim(val);
		if(obj[i].id==val){
			return obj[i].name;
		}
	}
}