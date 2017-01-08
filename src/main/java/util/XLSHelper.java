package util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Aleksey on 21.12.2016.
 */
public abstract class XLSHelper{

    private static FileChooser fileChooser = setFileChooser();

    private static FileChooser setFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx")
        );
        return fileChooser;
    }

    public static void saveInfo(){
        String fileName;
        File file = fileChooser.showSaveDialog(new Stage());
        fileName = file.getAbsolutePath();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Лікарі");

        Map<String, Object[]> data = new HashMap<String, Object[]>();
        data.put("1", new Object[] {"id", "ПІБ", "Спеціалізація", "Відділення", "Години прийому"});
        for (int i = 0; i < DBControl.doctors.size(); i++) {
            data.put(String.valueOf(i + 2), new Object[]{
                    String.valueOf(i + 1),
                    DBControl.doctors.get(i).getName(),
                    DBControl.doctors.get(i).getSpeciality().toString(),
                    DBControl.doctors.get(i).getDepartment().toString(),
                    DBControl.doctors.get(i).getTime().toString()
            });
        }

        pushDataToSheet(data, sheet);

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pushDataToSheet(Map<String, Object[]> data, XSSFSheet sheet){
        Set<String> keySet = data.keySet();
        int rowNum = 0;
        for (String key : keySet) {
            Row row = sheet.createRow(rowNum++);
            Object [] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
    }

}
