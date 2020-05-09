package cm.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


public class ExportExcelUtils {
	private static HSSFWorkbook wb;
	private static HSSFSheet sheet;
	  
	/** 
	 * @param wb 
	 * @param sheet  
	 */  
	public ExportExcelUtils(HSSFWorkbook wb, HSSFSheet sheet) {
	    this.wb = wb;  
	    this.sheet = sheet;  
	}  
	public ExportExcelUtils() {  
	}
	/** 
	 * 创建通用EXCEL头部  标题居中
	 *  
	 * @param colSum 
	 *        该报表的列数 
	  */  
	@SuppressWarnings({ "deprecation", "unused" })  
	public int createNormalHead(String headString,int rowNum,int colSum,int fontHeight,short style) {  
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short) 500);
		// 设置第一行  
		HSSFCell cell = row.createCell(0);
		// 定义单元格为字符串类型  
//		cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理  
		cell.setCellValue(new HSSFRichTextString(headString));
		// 指定合并区域  
		/** 
		 * public CellRangeAddress(int rowFrom, short colFrom, int rowTo, short colTo) 
		 */  
		sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum, 0,colSum));
		// 定义单元格格式，添加单元格表样式，并添加到工作簿  
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型  
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short)fontHeight);  
		cellStyle.setFont(font);  
		cell.setCellStyle(cellStyle);
		setRegionStyle(sheet,new CellRangeAddress(rowNum,rowNum, 0,colSum),cellStyle);
		rowNum++;
		return rowNum;
	} 
	/** 
	 * 创建通用报表指定行 
	 *  
	 * @param params 
	 *            统计条件数组 
	 * @param colSum 
	 *            需要合并到的列索引 
	 */  
	 @SuppressWarnings("deprecation")  
	 public int createNormalRow(String params,int rowNum, int colSum,short style) {  
		 // 创建第row行  
		 HSSFRow row = sheet.createRow(rowNum);
		 row.setHeight((short) 350);
		 HSSFCell cell2 = row.createCell(0);
		 cell2.setCellValue(new HSSFRichTextString(params));
		 // 指定合并区域  
		 sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,0,colSum));
		 HSSFCellStyle cellStyle = wb.createCellStyle();
		 cellStyle.setAlignment(style); // 指定单元格居中对齐  
		 cellStyle.setWrapText(true);// 指定单元格自动换行  
		 cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		 cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		 cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		 cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		 // 设置单元格字体  
		 HSSFFont font = wb.createFont();
		 font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		 font.setFontName("Arial");  
		 cellStyle.setFont(font);  
		 cell2.setCellStyle(cellStyle);
		 setRegionStyle(sheet,new CellRangeAddress(rowNum,rowNum, 0,colSum),cellStyle);
		 rowNum++;
		 return rowNum;
	}  
	/** 
	 * 设置报表列名 
	 *  
	 * @param columHeader 
	 *            标题字符串数组 
	 */  
	@SuppressWarnings("deprecation")
	public int createColumHeader(int rowNum,List<String> columList) {  
		// 设置列头 在第三行  
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short) 300);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		// 单元格字体  
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("Arial");  
		cellStyle.setFont(font);  
		HSSFCell cell3 = null;
		for (int i = 0; i < columList.size(); i++) {  
			cell3 = row.createCell(i);  
			cell3.setCellValue(new HSSFRichTextString(columList.get(i)));
			cell3.setCellStyle(cellStyle);
		} 
		rowNum++;
		return rowNum;
	}  
	  
	/** 
	 * 创建内容单元格 
	 *  
	 * @param wb 
	 *            HSSFWorkbook 
	 * @param row 
	 *            HSSFRow 
	 * @param col 
	 *            short型的列索引 
	 * @param align 
	 *            对齐方式 
	 * @param val 
	 *            列值 
	 */  
	@SuppressWarnings("deprecation")
	public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, String val, HSSFCellStyle cellStyle) {
		HSSFCell cell = row.createCell(col);
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(val);  
		cell.setCellStyle(cellStyle);  
	}  
	@SuppressWarnings("deprecation")
	public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, Double val, HSSFCellStyle cellStyle) {
		HSSFCell cell = row.createCell(col);
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(val);  
		cell.setCellStyle(cellStyle);  
	}
	/** 
	 * 创建合计行 
	 *  
	 * @param colSum 
	 *            需要合并到的行索引 
	 * @param cellValue 
	 */  
	@SuppressWarnings("deprecation")  
	public int createLastSumRow(int row,int colSum, Double[] cellValue,int firstRow, int lastRow, int firstCol, int lastCol,String str) {  
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat format= wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("#,##0.00"));
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		//单元格字体  
		//货币格式
		if ("CNY".equals(str)) {
			cellStyle.setDataFormat(format.getFormat("¥#,##0.00"));
		} else if ("USD".equals(str)) {
			cellStyle.setDataFormat(format.getFormat("$#,##0.00"));
		} else if ("EUR".equals(str)) {
			cellStyle.setDataFormat(format.getFormat("€#,##0.00"));
		} else {
			cellStyle.setDataFormat(format.getFormat("#,##0.00"));
		} 
		//获取工作表最后一行  
		HSSFRow lastrow = sheet.createRow(row);
		lastrow.setHeight((short) 350);
		HSSFCell sumCell = lastrow.createCell(0);
		sumCell.setCellValue(new HSSFRichTextString("合计 Total"));
		sumCell.setCellStyle(cellStyle);  
		//合并 最后一行的第零列-最后一行的第n列
		sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstCol,lastCol));//指定合并区域
		double sum = 0;
		if(null!=cellValue && cellValue.length>0){
			for (int i = 0; i < cellValue.length; i++) {  
				sum = cellValue[i] + sum;
			}
		}
		sumCell = lastrow.createCell(colSum);  
		sumCell.setCellValue(sum);
		sumCell.setCellStyle(cellStyle);
		setRegionStyle(sheet,new CellRangeAddress(firstRow,lastRow, 0,colSum),cellStyle);
		row++;
		return row;
	} 
	/** 
	 * 创建合计行 
	 *  
	 * @param colSum 
	 *            需要合并到的行索引 
	 * @param cellValue 
	 */  
	@SuppressWarnings("deprecation")  
	public void createLastSumRow(int colSum, Double[] cellValue, HSSFRow lastrow, String str) {
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行  
		HSSFDataFormat format= wb.createDataFormat();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		//货币格式
		if("CNY".equals(str)){
			cellStyle.setDataFormat(format.getFormat("¥#,##0.00"));
		}else if("USD".equals(str)){
			cellStyle.setDataFormat(format.getFormat("$#,##0.00"));
		}else if("EUR".equals(str)){
			cellStyle.setDataFormat(format.getFormat("€#,##0.00"));
		}else{
			cellStyle.setDataFormat(format.getFormat("#,##0.00"));
		}
		// 获取工作表最后一行  
		HSSFCell sumCell = lastrow.createCell(colSum);
		sumCell.setCellStyle(cellStyle);  
		double sum = 0;
		if(null!=cellValue){
			for (int i = 0; i < cellValue.length; i++) {  
				sum = cellValue[i] + sum;
			}
		}
		sumCell = lastrow.createCell(colSum);  
		sumCell.setCellStyle(cellStyle);
		sumCell.setCellValue(sum);
	}
	/**
	* 设置单元格边框（解决合并单元格显示部分边框问题）
	* @param sheet 
	* @param region
	* @param cs
	*/
	@SuppressWarnings("deprecation")
	public void setRegionStyle(HSSFSheet sheet, CellRangeAddress region, HSSFCellStyle cs) {
		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
				cell.setCellStyle(cs);
		 }
		}
	}
	
	/**
	 * 输出文件
	 * @param fileName
	 */
	public void  outputExcel(String fileName , HttpServletResponse response) {
	
	/*	try {  
		
			FileOutputStream fos = null;
			File directory = new File("model");
			String savePath = null;
			savePath = directory.getCanonicalPath()+File.separator;
			File file = new File(savePath);
			String path = "";
			//判断文件夹是否存在,如果不存在则创建文件夹
			if (!file.exists()) {
			  file.mkdirs();
			}
			path = fileName + ".xls";
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		}catch (Exception e) {
			// TODO: handle exception
		}*/
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
	    
		try {
			response.reset();
			wb.write(os);
	    	  byte[] content = os.toByteArray();
		      InputStream is = new ByteArrayInputStream(content);
		      response.setCharacterEncoding("utf-8");  
		      response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1")+".xls");
		      bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(response.getOutputStream());
			bos.flush();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = bis.read(b)) > 0) {
				bos.write(b, 0, len);
			}
			bos.flush();
			bos.close();
			bis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	     
	}

	 
	/** 
	 * @return the sheet 
	 */  
	public HSSFSheet getSheet() {
		return sheet;  
	}  
	  
	/** 
	 * @param sheet 
	 *            the sheet to set 
	 */  
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;  
	}  
	  
	/** 
	 * @return the wb 
	 */  
	public HSSFWorkbook getWb() {
		return wb;  
	}  
	  
	/** 
	 * @param wb 
	 *            the wb to set 
	 */  
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;  
	}  
}
