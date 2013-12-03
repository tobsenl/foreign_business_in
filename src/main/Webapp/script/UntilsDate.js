var m = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];//1-12月每个月对应的天数.
var year_, month_, day_;//定义全局变量存放计算好的年月日
function getMonth(imonth, type) { //通过输入的月跟类型来获取年月
	if (type != 1) {//如果不是计算出来的值 是输入的
		var temp_v = imonth / 12;	//输入的除以一年的月份数 
		var size_v = (temp_v + "").split(".");//看是否被整除
		if (size_v.length == 2) {//如果没有整除
			imonth = month_ + imonth;//当前月+输入月
		}
	}
	var size = imonth / 12;//用计算好的月份 可能是输入月+当前月 也可能直接是输入月(能够整除的输入月)除以一年=能够获得年
	var t_size_year = 0;//设置临时变量
	t_size_year = Math.floor(size);//获取年的整数部分.去除小数部分.此处小数部分舍去.
	year_ = year_ + t_size_year;//当前年+增加的年份.为真实计算所要年份
	var month_v = imonth - (t_size_year * 12);//用月份减去增加的月份数(增加的年的整数部分*12) 等于剩下的月份.
	if (month_v != 0) {//如果剩下的月份不为0
		month_ = month_v;//则剩下的月份为当前月份
	}
}
function getDay(iday) {
	iday = day_ + iday;//将当前天数+输入天数
	var chushu = 1;//除数默认为1 因为任何数/1都为原数
	if (month_ === 2 && (year_ / 4 == 0 && year_ / 100 != 0)//判断如果为2月的话是否为闰年
			|| (year_ / 400 == 0)) {
		chushu = m[month_ - 1] + 1;//为闰年的话当月天数+1
	} else {
		chushu = m[month_ - 1];//如果不为闰年的话通过对应的数组下标取monthday
	}
	var t_size_day = iday / chushu;//总天数数以当月天数
	if (t_size_day < 1) {//如果计算结果小于1 则monthlength为1
		t_size_day = 1;
	} else {//如果大于等于1的话 舍去小数部分
		t_size_day = Math.floor(t_size_day);
	}
	day_ = iday - (t_size_day * chushu);//用总的天数-月份天数=剩余天数

	if (day_ > 0) {//如果剩余天数>0 则当前月+增加的月份 用来调用getmonth方法重新计算年月
		var temp_month = month_ + t_size_day;
		getMonth(temp_month, 1);
	} else if (day_ == 0) {//如果剩余天数为0 则表示增加了*个月 day无变化.
		day_ = chushu;
	} else {
		day_ = chushu + day_; //如果小于0 则表示当前月的day扣除*day为真实day day_为负数.
	}
}