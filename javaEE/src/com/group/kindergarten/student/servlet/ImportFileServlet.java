package com.group.kindergarten.student.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.group.kindergarten.student.entity.Students;
import com.group.kindergarten.student.service.StudentsService;

/**
 * Servlet implementation class ImportFileServlet
 */
@WebServlet("/ImportFileServlet")
public class ImportFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("ImportFileServlet");
		List<Students> students = new ArrayList<>();
		FileItem itemFile = null;
		boolean a = false;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> listItem = upload.parseRequest(request);
			for (FileItem item : listItem) {
				if (!item.isFormField()) {// 判断是否为文件
					if (item.getFieldName().equals("fileName")) {
						if (item.getName() != null && !item.getName().equals("")) {
							itemFile = item;
						}
//						System.out.println(user);
					}
				}
			}
			// 创建输入流，接受目标excel文件
			FileInputStream in;
			// 先将文件保存在服务器里 并规定文件名称
			String path = this.getServletContext().getRealPath("")+"/imgs/students/students.xlsx";
			if (itemFile != null) {
				itemFile.write(new File(path));
				a = true;
			}
			if (a) {
				// 读取文件内容 将内容封装成List<Student>集合
				in = new FileInputStream(path);
				// 得到Excel工作簿对象
				XSSFWorkbook wk = new XSSFWorkbook(in);
				// 得到Excel工作簿的第一页，即excel工作表对象
				XSSFSheet sheet = wk.getSheetAt(0);
				// 遍历工作表
				// 遍历行对象
				for (Row row : sheet) {
					// 获取行索引
					int i = row.getRowNum();
					if (i > 0) {// 从第二行开始讲第7列的所有期末成绩都保存到数组中
						Students student = new Students();
						row.getCell(0).setCellType(Cell.CELL_TYPE_NUMERIC);
						student.setId(Integer.parseInt(row.getCell(0).getStringCellValue()));
						row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						student.setUserNumber(row.getCell(1).getStringCellValue());
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						student.setBabyName(row.getCell(2).getStringCellValue());
						row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						student.setBabyClass(row.getCell(3).getStringCellValue());
						row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						student.setBabyBirthday(row.getCell(4).getStringCellValue());
						row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
						student.setBabySex(row.getCell(5).getStringCellValue());
						row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
						student.setBabyIDnumber(row.getCell(6).getStringCellValue());
						row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
						student.setBabyAddoAllergies(row.getCell(7).getStringCellValue());
						
						row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
						student.setParentName1(row.getCell(8).getStringCellValue());
						row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
						student.setRelation1(row.getCell(9).getStringCellValue());
						row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
						student.setParentIDnumber1(row.getCell(10).getStringCellValue());
						row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
						student.setPhoneNumber1(row.getCell(11).getStringCellValue());
						row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
						student.setWorkSpace1(row.getCell(12).getStringCellValue());
						row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
						student.setHomeAddress1(row.getCell(13).getStringCellValue());
						
						row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
						student.setParentName2(row.getCell(14).getStringCellValue());
						row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
						student.setRelation2(row.getCell(15).getStringCellValue());
						row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
						student.setParentIDnumber2(row.getCell(16).getStringCellValue());
						row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
						student.setPhoneNumber2(row.getCell(17).getStringCellValue());
						row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
						student.setWorkSpace2(row.getCell(18).getStringCellValue());
						row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
						student.setHomeAddress2(row.getCell(19).getStringCellValue());

						System.out.println(student.toString());
						students.add(student);
					}
				}
				// 关闭文件输入流
				in.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将数据保存到数据库
		if (students != null && students.size() != 0) {
			boolean b = StudentsService.getInstance().insertDataAboutStudent(students);
			// 保存成功 跳转到展示界面 展示所有信息
			if (b && a) {
				request.getRequestDispatcher("StudentsManageServlet").forward(request, response);
			} else {
				System.out.println("保存失败！");
			}
		} else {
			System.out.println("文件信息未读取到！");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
