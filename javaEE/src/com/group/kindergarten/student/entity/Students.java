package com.group.kindergarten.student.entity;

public class Students {
    private int id;
    private String userNumber;//��ǰ�ҳ��ֻ���
    private String babyName;//��������
    private String babyClass;//�����༶
    private String babyBirthday;//��������
    private String babySex;//�����Ա�
    private String babyIDnumber;//�������֤��
    private String babyAddoAllergies;//��������ʳ��


    private String parentName1;//�ҳ�����1
    private String relation1;//�뱦����ϵ1
    private String parentIDnumber1;//�ҳ����֤��1
    private String phoneNumber1;//��ϵ��ʽ1
    private String workSpace1;//������λ1
    private String homeAddress1;//��ͥסַ1


    private String parentName2;//�ҳ�����2
    private String relation2;//�뱦����ϵ2
    private String parentIDnumber2;//�ҳ����֤��1
    private String phoneNumber2;//��ϵ��ʽ2
    private String workSpace2;//������λ2
    private String homeAddress2;//��ͥסַ2

    public Students() {
    }

    
    

	public Students(int id, String userNumber, String babyName, String babyClass,String babyBirthday, String babySex,
			String babyIDnumber, String babyAddoAllergies, String parentName1, String relation1, String parentIDnumber1,
			String phoneNumber1, String workSpace1, String homeAddress1, String parentName2, String relation2,
			String parentIDnumber2, String phoneNumber2, String workSpace2, String homeAddress2) {
		this.id = id;
		this.userNumber = userNumber;
		this.babyName = babyName;
		this.babyClass = babyClass;
		this.babyBirthday = babyBirthday;
		this.babySex = babySex;
		this.babyIDnumber = babyIDnumber;
		this.babyAddoAllergies = babyAddoAllergies;
		this.parentName1 = parentName1;
		this.relation1 = relation1;
		this.parentIDnumber1 = parentIDnumber1;
		this.phoneNumber1 = phoneNumber1;
		this.workSpace1 = workSpace1;
		this.homeAddress1 = homeAddress1;
		this.parentName2 = parentName2;
		this.relation2 = relation2;
		this.parentIDnumber2 = parentIDnumber2;
		this.phoneNumber2 = phoneNumber2;
		this.workSpace2 = workSpace2;
		this.homeAddress2 = homeAddress2;
	}




	public String getParentIDnumber1() {
		return parentIDnumber1;
	}




	public void setParentIDnumber1(String parentIDnumber1) {
		this.parentIDnumber1 = parentIDnumber1;
	}




	public String getParentIDnumber2() {
		return parentIDnumber2;
	}




	public void setParentIDnumber2(String parentIDnumber2) {
		this.parentIDnumber2 = parentIDnumber2;
	}




	public String getUserNumber() {
		return userNumber;
	}



	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}



	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBabyClass() {
		return babyClass;
	}




	public void setBabyClass(String babyClass) {
		this.babyClass = babyClass;
	}




	public String getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(String babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    public String getBabySex() {
        return babySex;
    }

    public void setBabySex(String babySex) {
        this.babySex = babySex;
    }

    public String getBabyIDnumber() {
        return babyIDnumber;
    }

    public void setBabyIDnumber(String babyIDnumber) {
        this.babyIDnumber = babyIDnumber;
    }

    public String getBabyAddoAllergies() {
        return babyAddoAllergies;
    }

    public void setBabyAddoAllergies(String babyAddoAllergies) {
        this.babyAddoAllergies = babyAddoAllergies;
    }

    public String getParentName1() {
        return parentName1;
    }

    public void setParentName1(String parentName1) {
        this.parentName1 = parentName1;
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getWorkSpace1() {
        return workSpace1;
    }

    public void setWorkSpace1(String workSpace1) {
        this.workSpace1 = workSpace1;
    }

    public String getHomeAddress1() {
        return homeAddress1;
    }

    public void setHomeAddress1(String homeAddress1) {
        this.homeAddress1 = homeAddress1;
    }

    public String getParentName2() {
        return parentName2;
    }

    public void setParentName2(String parentName2) {
        this.parentName2 = parentName2;
    }

    public String getRelation2() {
        return relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getWorkSpace2() {
        return workSpace2;
    }

    public void setWorkSpace2(String workSpace2) {
        this.workSpace2 = workSpace2;
    }

    public String getHomeAddress2() {
        return homeAddress2;
    }

    public void setHomeAddress2(String homeAddress2) {
        this.homeAddress2 = homeAddress2;
    }




	@Override
	public String toString() {
		return "Students [id=" + id + ", userNumber=" + userNumber + ", babyName=" + babyName + ", babyClass="
				+ babyClass + ", babyBirthday=" + babyBirthday + ", babySex=" + babySex + ", babyIDnumber="
				+ babyIDnumber + ", babyAddoAllergies=" + babyAddoAllergies + ", parentName1=" + parentName1
				+ ", relation1=" + relation1 + ", parentIDnumber1=" + parentIDnumber1 + ", phoneNumber1=" + phoneNumber1
				+ ", workSpace1=" + workSpace1 + ", homeAddress1=" + homeAddress1 + ", parentName2=" + parentName2
				+ ", relation2=" + relation2 + ", parentIDnumber2=" + parentIDnumber2 + ", phoneNumber2=" + phoneNumber2
				+ ", workSpace2=" + workSpace2 + ", homeAddress2=" + homeAddress2 + "]";
	}



	


    

	
    
}
