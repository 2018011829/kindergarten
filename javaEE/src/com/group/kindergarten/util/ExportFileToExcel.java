package com.group.kindergarten.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.applyinfo.service.EnterService;

/**
 * Servlet implementation class ExportFileToExcel
 */
@WebServlet("/ExportFileToExcel")
public class ExportFileToExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportFileToExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("ExportFileToExcel");
		//�����ݿ���Ѱ�����еı�����Ϣ
		List<ApplyInfo> list=EnterService.getInstance().searchAllChild();
		//���� HSSFWorkbook����
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//���� HSSFSheet����
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("sheet");
		//����HSSFRow��  0�����ŵ�һ�У�1�����ŵڶ��У��Դ����ƣ�
		HSSFRow row = hssfSheet.createRow(0);
		//����HSSFCell 0�����еĵ�һ����Ԫ��1����ڶ���
		//����HSSFCell�����������ı�ֵ
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellValue("���");
		HSSFCell cell1 = row.createCell(1);
		cell1.setCellValue("��ǰ�ҳ��ֻ���");
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellValue("��������");
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellValue("��������");
		HSSFCell cell4 = row.createCell(4);
		cell4.setCellValue("�����Ա�");
		HSSFCell cell5 = row.createCell(5);
		cell5.setCellValue("�������֤��");
		HSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("��������ʳ��");
		HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("�ҳ�����1");
		HSSFCell cell8 = row.createCell(8);
		cell8.setCellValue("�뱦����ϵ1");
		HSSFCell cell9 = row.createCell(9);
		cell9.setCellValue("�ҳ����֤��1");
		HSSFCell cell10 = row.createCell(10);
		cell10.setCellValue("��ϵ��ʽ1");
		HSSFCell cell11 = row.createCell(11);
		cell11.setCellValue("������λ1");
		HSSFCell cell12 = row.createCell(12);
		cell12.setCellValue("��ͥסַ1");
		HSSFCell cell13 = row.createCell(13);
		cell13.setCellValue("�ҳ�����2");
		HSSFCell cell14 = row.createCell(14);
		cell14.setCellValue("�뱦����ϵ2");
		HSSFCell cell15 = row.createCell(15);
		cell15.setCellValue("�ҳ����֤��2");
		HSSFCell cell16 = row.createCell(16);
		cell16.setCellValue("��ϵ��ʽ2");
		HSSFCell cell17 = row.createCell(17);
		cell17.setCellValue("������λ2");
		HSSFCell cell18 = row.createCell(18);
		cell18.setCellValue("��ͥסַ2");
		//�������� �������е�������������Excel����
		for(int i=0;i<list.size();++i) {
			ApplyInfo applyInfo=list.get(i);
			HSSFRow rows = hssfSheet.createRow(i+1);
			//����HSSFCell 0�����еĵ�һ����Ԫ��1����ڶ���
			//����HSSFCell�����������ı�ֵ
			HSSFCell cell0s = rows.createCell(0);
			cell0s.setCellValue(applyInfo.getId());
			HSSFCell cell1s = rows.createCell(1);
			cell1s.setCellValue(applyInfo.getUserNumber());
			HSSFCell cell2s = rows.createCell(2);
			cell2s.setCellValue(applyInfo.getBabyName());
			HSSFCell cell3s = rows.createCell(3);
			cell3s.setCellValue(applyInfo.getBabyBirthday());
			HSSFCell cell4s = rows.createCell(4);
			cell4s.setCellValue(applyInfo.getBabySex());
			HSSFCell cell5s = rows.createCell(5);
			cell5s.setCellValue(applyInfo.getBabyIDnumber());
			HSSFCell cell6s = rows.createCell(6);
			cell6s.setCellValue(applyInfo.getBabyAddoAllergies());
			HSSFCell cell7s = rows.createCell(7);
			cell7s.setCellValue(applyInfo.getParentName1());
			HSSFCell cell8s = rows.createCell(8);
			cell8s.setCellValue(applyInfo.getRelation1());
			HSSFCell cell9s = rows.createCell(9);
			cell9s.setCellValue(applyInfo.getParentIDnumber1());
			HSSFCell cell10s = rows.createCell(10);
			cell10s.setCellValue(applyInfo.getPhoneNumber1());
			HSSFCell cell11s = rows.createCell(11);
			cell11s.setCellValue(applyInfo.getWorkSpace1());
			HSSFCell cell12s = rows.createCell(12);
			cell12s.setCellValue(applyInfo.getHomeAddress1());
			HSSFCell cell13s = rows.createCell(13);
			cell13s.setCellValue(applyInfo.getParentName2());
			HSSFCell cell14s = rows.createCell(14);
			cell14s.setCellValue(applyInfo.getRelation2());
			HSSFCell cell15s = rows.createCell(15);
			cell15s.setCellValue(applyInfo.getParentIDnumber2());
			HSSFCell cell16s = rows.createCell(16);
			cell16s.setCellValue(applyInfo.getPhoneNumber2());
			HSSFCell cell17s = rows.createCell(17);
			cell17s.setCellValue(applyInfo.getWorkSpace2());
			HSSFCell cell18s = rows.createCell(18);
			cell18s.setCellValue(applyInfo.getHomeAddress2());
		}
		//Ȼ������ļ�д��
		FileOutputStream out = new FileOutputStream("D:\\������Ϣ.xls");
		hssfWorkbook.write(out);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
