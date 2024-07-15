package FileInputOut;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Exam14Jul2024 {
    public static void main(String[] args) throws IOException {
        {


            File file = new File("C:\\zorba_intellije\\MavenProject\\src\\main\\resources\\employee.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);


            Row row = sheet.createRow(0);
            if (sheet.getFirstRowNum() == 0) {
                row.createCell(0, CellType.NUMERIC).setCellValue("Employee_id");
                row.createCell(1, CellType.STRING).setCellValue("Employee_name");
                row.createCell(2, CellType.NUMERIC).setCellValue("Emp_Salary");
                row.createCell(3, CellType.STRING).setCellValue("Emp_mobile");
                row.createCell(4, CellType.STRING).setCellValue("Emp_city ");


                Cell cell;
                int no_of_cell = sheet.getRow(0).getLastCellNum();


                Row row1 = sheet.createRow(sheet.getLastRowNum() + 1);

                row1.createCell(0, CellType.NUMERIC).setCellValue(1001);
                row1.createCell(1, CellType.STRING).setCellValue("Jack");
                row1.createCell(2, CellType.NUMERIC).setCellValue(1482.45);
                row1.createCell(3, CellType.STRING).setCellValue("0809808008");
                row.createCell(4, CellType.STRING).setCellValue("NYC ");

                Row row2 = sheet.createRow(sheet.getLastRowNum() + 1);

                row2.createCell(0, CellType.NUMERIC).setCellValue(1002);
                row2.createCell(1, CellType.STRING).setCellValue("Joy");
                row2.createCell(2, CellType.NUMERIC).setCellValue(5282.12);
                row2.createCell(3, CellType.STRING).setCellValue("9809808008");
                row2.createCell(4, CellType.STRING).setCellValue("SD");


                           /*
            *
            *
Employee_id 	Employee_name Emp_Salary Emp_mobile 	Emp_city
1001	Jack  	 	1482.45 	0809808008 	NYC
1002	Joy 	 	 	5282.12 	9809808008 	SD
1003	Nick  	 	3454.11 	8976876786 	Dayton
1004	Joe 	 	 	6482.45 	8809808008 	NYC
1005	Nick  	 	5482.45 	5809808008 	CA
1006	Hyder  	 	9482.45 	2809808008 	LA
1007	Harry  	 	1182.45 	4809808008 	Ohio

            * */


                Row row3 = sheet.createRow(sheet.getLastRowNum() + 1);

                row3.createCell(0, CellType.NUMERIC).setCellValue(1003);
                row3.createCell(1, CellType.STRING).setCellValue("Nick");
                row3.createCell(2, CellType.NUMERIC).setCellValue(3454.11);
                row3.createCell(3, CellType.STRING).setCellValue("8976876786");
                row3.createCell(4, CellType.STRING).setCellValue("Dayton");

                Row row4 = sheet.createRow(sheet.getLastRowNum() + 1);

                row4.createCell(0, CellType.NUMERIC).setCellValue(1004);
                row4.createCell(1, CellType.STRING).setCellValue("Joe");
                row4.createCell(2, CellType.NUMERIC).setCellValue(6482.45);
                row4.createCell(3, CellType.STRING).setCellValue("8809808008");
                row4.createCell(4, CellType.STRING).setCellValue("NYC");


                Row row5 = sheet.createRow(sheet.getLastRowNum() + 1);

                row5.createCell(0, CellType.NUMERIC).setCellValue(1005);
                row5.createCell(1, CellType.STRING).setCellValue("Nick");
                row5.createCell(2, CellType.NUMERIC).setCellValue(5482.45);
                row5.createCell(3, CellType.STRING).setCellValue("5809808008 ");
                row5.createCell(4, CellType.STRING).setCellValue("CA");

                Row row6 = sheet.createRow(sheet.getLastRowNum() + 1);

                row6.createCell(0, CellType.NUMERIC).setCellValue(1006);
                row6.createCell(1, CellType.STRING).setCellValue("Hyder");
                row6.createCell(2, CellType.NUMERIC).setCellValue(9482.45);
                row6.createCell(3, CellType.STRING).setCellValue("2809808008");
                row6.createCell(4, CellType.STRING).setCellValue("LA");

                Row row7 = sheet.createRow(sheet.getLastRowNum() + 1);

                row7.createCell(0, CellType.NUMERIC).setCellValue(1007);
                row7.createCell(1, CellType.STRING).setCellValue("Harry");
                row7.createCell(2, CellType.NUMERIC).setCellValue(1182.45);
                row7.createCell(3, CellType.STRING).setCellValue("4809808008");
                row7.createCell(4, CellType.STRING).setCellValue("Ohio");


                FileOutputStream outputStream = new FileOutputStream(file);
                xssfWorkbook.write(outputStream);

                outputStream.close();
                System.out.println("successful write on excel");

            }
        }
    }
}
