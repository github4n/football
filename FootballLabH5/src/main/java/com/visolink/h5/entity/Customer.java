package com.visolink.h5.entity;

/**
 * @className Customer.java
 * @author Administrator
 * @createTime 2015年5月8日 下午3:11:30
 * @version
 */
public class Customer {
	private Integer ID;
	
	/**
	 * 会员ID
	 */
	private Integer MemberID;
	/**
	 * 现房/期房
	 */
	private String HouType;
	/**
	 * 建筑类型
	 */
	private String HouBuildingTypes;
	/**
	 * 小区名称
	 */
	private String HouVillage;
	/**
	 * 户型
	 */
	private String HouApartment;
	/**
	 * 装修预算
	 */
	private String HouBudget;
	/**
	 * 工程地址
	 */
	private String HouAddr;
	/**
	 * 面积
	 */
	private String HouArea;
	/**
	 * 渠道来源
	 */
	private String MemChannel;
	/**
	 * 业务类型
	 */
	private String BusinessType;
	/**
	 * 邮箱
	 */
	private String MemMail;
	/**
	 * 手机号码
	 */
	private String MemPhoneNum;
	/**
	 * 密码
	 */
	private String MemPswd;
	/**
	 * 姓名
	 */
	private String MemName;
	/**
	 * 身份证号
	 */
	private String MemIDNumber;
	/**
	 * 昵称
	 */
	private String MemNickname;
	/**
	 * 性别
	 */
	private String MemSex;
	/**
	 * 年龄
	 */
	private Integer MemAge;
	/**
	 * 出生年月日
	 */
	private String MemBirth;
	/**
	 * 星座
	 */
	private String MemConstellation;
	/**
	 * 血型
	 */
	private String MemBloodType;
	/**
	 * 家庭住址
	 */
	private String Memaddr;
	/**
	 * 家庭成员
	 */
	private String MemFamily;
	/**
	 * 婚姻状况
	 */
	private String MemIsMarry;
	/**
	 * 育否
	 */
	private String MemIsBear;
	/**
	 * 职业
	 */
	private String MemOccupation;
	/**
	 * 微信
	 */
	private String MemWechat;
	/**
	 * 微博
	 */
	private String MemMicroblog;
	/**
	 * QQ
	 */
	private String MemQQ;
	/**
	 * 城市
	 */
	private String MemCity;
	/**
	 * 会员类型
	 */
	private String MemType;
	/**
	 * 会员级别
	 */
	private String MemLevel;
	/**
	 * 点赞数
	 */
	private Integer MemPointsNum;
	/**
	 * 评论数
	 */
	private Integer MemCommentsNum;
	/**
	 * 分享数
	 */
	private Integer MemShareNum;
	/**
	 * 消费金额
	 */
	private Float MemConsumMoney;
	/**
	 * 住宅信息ID
	 */
	private Integer HouID;
	/**
	 * 客户备注
	 */
	private String CustComment;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getMemberID() {
		return MemberID;
	}
	public void setMemberID(Integer memberID) {
		MemberID = memberID;
	}
	public String getHouType() {
		return HouType;
	}
	public void setHouType(String houType) {
		HouType = houType;
	}
	public String getHouBuildingTypes() {
		return HouBuildingTypes;
	}
	public void setHouBuildingTypes(String houBuildingTypes) {
		HouBuildingTypes = houBuildingTypes;
	}
	public String getHouVillage() {
		return HouVillage;
	}
	public void setHouVillage(String houVillage) {
		HouVillage = houVillage;
	}
	public String getHouApartment() {
		return HouApartment;
	}
	public void setHouApartment(String houApartment) {
		HouApartment = houApartment;
	}
	public String getHouBudget() {
		return HouBudget;
	}
	public void setHouBudget(String houBudget) {
		HouBudget = houBudget;
	}
	public String getHouAddr() {
		return HouAddr;
	}
	public void setHouAddr(String houAddr) {
		HouAddr = houAddr;
	}
	public String getHouArea() {
		return HouArea;
	}
	public void setHouArea(String houArea) {
		HouArea = houArea;
	}
	public String getMemChannel() {
		return MemChannel;
	}
	public void setMemChannel(String memChannel) {
		MemChannel = memChannel;
	}
	public String getBusinessType() {
		return BusinessType;
	}
	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}
	public String getMemMail() {
		return MemMail;
	}
	public void setMemMail(String memMail) {
		MemMail = memMail;
	}
	public String getMemPhoneNum() {
		return MemPhoneNum;
	}
	public void setMemPhoneNum(String memPhoneNum) {
		MemPhoneNum = memPhoneNum;
	}
	public String getMemPswd() {
		return MemPswd;
	}
	public void setMemPswd(String memPswd) {
		MemPswd = memPswd;
	}
	public String getMemName() {
		return MemName;
	}
	public void setMemName(String memName) {
		MemName = memName;
	}
	public String getMemIDNumber() {
		return MemIDNumber;
	}
	public void setMemIDNumber(String memIDNumber) {
		MemIDNumber = memIDNumber;
	}
	public String getMemNickname() {
		return MemNickname;
	}
	public void setMemNickname(String memNickname) {
		MemNickname = memNickname;
	}
	public String getMemSex() {
		return MemSex;
	}
	public void setMemSex(String memSex) {
		MemSex = memSex;
	}
	public Integer getMemAge() {
		return MemAge;
	}
	public void setMemAge(Integer memAge) {
		MemAge = memAge;
	}
	public String getMemBirth() {
		return MemBirth;
	}
	public void setMemBirth(String memBirth) {
		MemBirth = memBirth;
	}
	public String getMemConstellation() {
		return MemConstellation;
	}
	public void setMemConstellation(String memConstellation) {
		MemConstellation = memConstellation;
	}
	public String getMemBloodType() {
		return MemBloodType;
	}
	public void setMemBloodType(String memBloodType) {
		MemBloodType = memBloodType;
	}
	public String getMemaddr() {
		return Memaddr;
	}
	public void setMemaddr(String memaddr) {
		Memaddr = memaddr;
	}
	public String getMemFamily() {
		return MemFamily;
	}
	public void setMemFamily(String memFamily) {
		MemFamily = memFamily;
	}
	public String getMemIsMarry() {
		return MemIsMarry;
	}
	public void setMemIsMarry(String memIsMarry) {
		MemIsMarry = memIsMarry;
	}
	public String getMemIsBear() {
		return MemIsBear;
	}
	public void setMemIsBear(String memIsBear) {
		MemIsBear = memIsBear;
	}
	public String getMemOccupation() {
		return MemOccupation;
	}
	public void setMemOccupation(String memOccupation) {
		MemOccupation = memOccupation;
	}
	public String getMemWechat() {
		return MemWechat;
	}
	public void setMemWechat(String memWechat) {
		MemWechat = memWechat;
	}
	public String getMemMicroblog() {
		return MemMicroblog;
	}
	public void setMemMicroblog(String memMicroblog) {
		MemMicroblog = memMicroblog;
	}
	public String getMemQQ() {
		return MemQQ;
	}
	public void setMemQQ(String memQQ) {
		MemQQ = memQQ;
	}
	public String getMemCity() {
		return MemCity;
	}
	public void setMemCity(String memCity) {
		MemCity = memCity;
	}
	public String getMemType() {
		return MemType;
	}
	public void setMemType(String memType) {
		MemType = memType;
	}
	public String getMemLevel() {
		return MemLevel;
	}
	public void setMemLevel(String memLevel) {
		MemLevel = memLevel;
	}
	public Integer getMemPointsNum() {
		return MemPointsNum;
	}
	public void setMemPointsNum(Integer memPointsNum) {
		MemPointsNum = memPointsNum;
	}
	public Integer getMemCommentsNum() {
		return MemCommentsNum;
	}
	public void setMemCommentsNum(Integer memCommentsNum) {
		MemCommentsNum = memCommentsNum;
	}
	public Integer getMemShareNum() {
		return MemShareNum;
	}
	public void setMemShareNum(Integer memShareNum) {
		MemShareNum = memShareNum;
	}
	public Float getMemConsumMoney() {
		return MemConsumMoney;
	}
	public void setMemConsumMoney(Float memConsumMoney) {
		MemConsumMoney = memConsumMoney;
	}
	public Integer getHouID() {
		return HouID;
	}
	public void setHouID(Integer houID) {
		HouID = houID;
	}
	public String getCustComment() {
		return CustComment;
	}
	public void setCustComment(String custComment) {
		CustComment = custComment;
	}
}
