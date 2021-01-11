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
		//从数据库中寻找所有的报名信息
		List<ApplyInfo> list=EnterService.getInstance().searchAllChild();
		//创建 HSSFWorkbook对象
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//生成 HSSFSheet对象
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("sheet");
		//生成HSSFRow行  0代表着第一行，1代表着第二行，以此类推；
		HSSFRow row = hssfSheet.createRow(0);
		//生成HSSFCell 0代表本行的第一个单元格，1代表第二个
		//利用HSSFCell对象进行填充文本值
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellValue("序号");
		HSSFCell cell1 = row.createCell(1);
		cell1.setCellValue("当前家长手机号");
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellValue("宝宝姓名");
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellValue("宝宝生日");
		HSSFCell cell4 = row.createCell(4);
		cell4.setCellValue("宝宝性别");
		HSSFCell cell5 = row.createCell(5);
		cell5.setCellValue("宝宝身份证号");
		HSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("宝宝过敏食物");
		HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("家长姓名1");
		HSSFCell cell8 = row.createCell(8);
		cell8.setCellValue("与宝宝关系1");
		HSSFCell cell9 = row.createCell(9);
		cell9.setCellValue("家长身份证号1");
		HSSFCell cell10 = row.createCell(10);
		cell10.setCellValue("联系方式1");
		HSSFCell cell11 = row.createCell(11);
		cell11.setCellValue("工作单位1");
		HSSFCell cell12 = row.createCell(12);
		cell12.setCellValue("家庭住址1");
		HSSFCell cell13 = row.createCell(13);
		cell13.setCellValue("家长姓名2");
		HSSFCell cell14 = row.createCell(14);
		cell14.setCellValue("与宝宝关系2");
		HSSFCell cell15 = row.createCell(15);
		cell15.setCellValue("家长身份证号2");
		HSSFCell cell16 = row.createCell(16);
		cell16.setCellValue("联系方式2");
		HSSFCell cell17 = row.createCell(17);
		cell17.setCellValue("工作单位2");
		HSSFCell cell18 = row.createCell(18);
		cell18.setCellValue("家庭住址2");
		//遍历集合 将集合中的数据依次填入Excel表中
		for(int i=0;i<list.size();++i) {
			ApplyInfo applyInfo=list.get(i);
			HSSFRow rows = hssfSheet.createRow(i+1);
			//生成HSSFCell 0代表本行的第一个单元格，1代表第二个
			//利用HSSFCell对象进行填充文本值
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
		//然后进行文件写入
		FileOutputStream out = new FileOutputStream("D:\\报名信息.xls");
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
