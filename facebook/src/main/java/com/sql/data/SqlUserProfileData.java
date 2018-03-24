package com.sql.data;


public class SqlUserProfileData {
	private String Name;
	private String Email;
	private String DOB;
	private String Password;
	private String Mobile;
	private String Hometown;
	private String Country;
	private String ProfileImage;
	private String Id;
	private String Works;
	private String Livesin;
	private String Gender;
	private String College;
	public String getWorks() {
		return Works;
	}
	public void setWorks(String works) {
		Works = works;
	}
	public String getLivesin() {
		return Livesin;
	}
	public void setLivesin(String livesin) {
		Livesin = livesin;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getCollege() {
		return College;
	}
	public void setCollege(String college) {
		College = college;
	}
	public SqlUserProfileData(){
	}
	public SqlUserProfileData(String Name,String Email,String DOB,String Password,String Mobile,String Hometown,
			String Country,String ProfileImage,
			String Works,String Livesin,String College,String Gender,String Id){
		this.Name=Name;
		this.DOB=DOB;
		this.Email=Email;
		this.Mobile=Mobile;
		this.Password=Password;
		this.Hometown=Hometown;
		this.Country=Country;
		this.ProfileImage=ProfileImage;
		this.Id=Id;
		this.College=College;
		this.Gender=Gender;
		this.Livesin=Livesin;
		this.Works=Works;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getProfileImage() {
		return ProfileImage;
	}
	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}
	public String getHometown() {
		return Hometown;
	}
	public void setHometown(String hometown) {
		Hometown = hometown;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
}

