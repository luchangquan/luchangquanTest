package com.renke.rdbao.web.support;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceCycleDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceSpecifiedDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceTotalTimeStatisticsVo;

/**
 * 报表统计生成
 * 
 * @author jgshun
 * @date 2017-1-5 下午5:20:33
 * @version 1.0
 */
public class VoiceStatisticsSupport {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceStatisticsSupport.class);

	/**
	 * 下载语音记录报表
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void downloadVoiceRecording(List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices, HttpServletResponse response) throws UnsupportedEncodingException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// List<String> titles = Lists.newArrayList("呼叫类型", "呼出号码", "呼入号码",
		// "录音开始", "录音结束", "通话时长", "存证公证处");
		List<String> titles = Lists.newArrayList("呼叫类型", "呼出号码", "呼入号码", "录音开始", "录音结束", "通话时长");

		this.appendSheetInfoForVoiceRecording(workbook, workbook.createSheet("记录"), titles, enhancedEvidenceFaxVoices);

		String downFileName = "语音报表-" + new DateTime().toString("yyyyMMddHHmm");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(downFileName.getBytes("utf-8"), "iso8859-1") + ".xls");
		response.setContentType("applicationnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		BufferedOutputStream os = null;
		try {
			// 执行写入操作***********************************
			os = new BufferedOutputStream(response.getOutputStream());
			workbook.write(os);
			os.flush();
		} catch (IOException e) {
			_LOGGER.error("[报表导出失败]", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					_LOGGER.error("[报表导出失败--流关闭失败]", e);
				}
			}
		}
	}

	private void appendSheetInfoForVoiceRecording(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titles, List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices) {
		HSSFRow firstRow = sheet.createRow((int) 1);

		CellStyle firstCellStyle = workbook.createCellStyle();// 首行
		firstCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		firstCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		firstCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		firstCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle contentCellStyle = workbook.createCellStyle();// 内容
		contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyle.setWrapText(true);
		contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle contentCellStyleTop = workbook.createCellStyle();// 内容顶部

		// HSSFPalette customPalette = workbook.getCustomPalette();//自定义颜色
		// 索引只能是 8 ~ 64
		// customPalette.setColorAtIndex((short) 13, (byte) 224, (byte) 238,
		// (byte) 238);
		// contentCellStyleTop.setFillForegroundColor((short) 13);
		// contentCellStyleTop.setFillPattern(CellStyle.SOLID_FOREGROUND);
		contentCellStyleTop.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyleTop.setWrapText(true);
		contentCellStyleTop.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		Font firstFont = workbook.createFont();// 首行文字
		firstFont.setFontName("华文中宋");// 设置字体名称
		firstFont.setFontHeightInPoints((short) 11);// 设置字号

		Font contentFont = workbook.createFont();// 内容文字
		firstFont.setFontName("华文中宋");// 设置字体名称
		firstFont.setFontHeightInPoints((short) 10);// 设置字号

		firstCellStyle.setFont(firstFont);
		contentCellStyle.setFont(contentFont);

		for (int i = 0; i < titles.size(); i++) {
			HSSFCell curCell = firstRow.createCell((short) i);
			curCell.setCellValue(titles.get(i));
			curCell.setCellType(Cell.CELL_TYPE_STRING);
			curCell.setCellStyle(firstCellStyle);
		}

		for (int i = 0; i < enhancedEvidenceFaxVoices.size(); i++) {
			HSSFRow curRow = sheet.createRow((short) (i + 2));
			EnhancedEvidenceFaxVoices _EnhancedEvidenceFaxVoices = enhancedEvidenceFaxVoices.get(i);

			// List<String> titles = Lists.newArrayList("呼叫类型", "呼出号码", "呼入号码",
			// "录音开始", "录音结束", "通话时长", "存证公证处");
			HSSFCell callTypeCell = curRow.createCell((short) 0);
			HSSFCell callingPhoneNoCell = curRow.createCell((short) 1);
			HSSFCell calledPhoneNoCell = curRow.createCell((short) 2);
			HSSFCell startTimeCell = curRow.createCell((short) 3);
			HSSFCell endTimeCell = curRow.createCell((short) 4);
			HSSFCell durationCell = curRow.createCell((short) 5);
			// HSSFCell pnoeCell = curRow.createCell((short) 6);

			callTypeCell.setCellType(Cell.CELL_TYPE_STRING);
			callTypeCell.setCellValue(_EnhancedEvidenceFaxVoices.getCallType().getDesc());

			callingPhoneNoCell.setCellType(Cell.CELL_TYPE_STRING);
			callingPhoneNoCell.setCellValue(_EnhancedEvidenceFaxVoices.getCallingNumber());

			calledPhoneNoCell.setCellType(Cell.CELL_TYPE_STRING);
			calledPhoneNoCell.setCellValue(_EnhancedEvidenceFaxVoices.getCalledNumber());

			startTimeCell.setCellType(Cell.CELL_TYPE_STRING);
			startTimeCell.setCellValue(new DateTime(_EnhancedEvidenceFaxVoices.getCallTime()).toString("yyyy-MM-dd HH:mm:ss"));

			endTimeCell.setCellType(Cell.CELL_TYPE_STRING);
			endTimeCell.setCellValue(new DateTime(_EnhancedEvidenceFaxVoices.getCallTime()).plusSeconds((int) _EnhancedEvidenceFaxVoices.getDuration()).toString("yyyy-MM-dd HH:mm:ss"));

			durationCell.setCellType(Cell.CELL_TYPE_NUMERIC);
			durationCell.setCellValue(_EnhancedEvidenceFaxVoices.getDuration());

			// curRow.setHeightInPoints((6 *
			// sheet.getDefaultRowHeightInPoints()));//设置行高度
		}
		// for (int i = 0; i < voiceLogReport2Model.getTitles().size(); i++)
		// {//自动调整列宽
		// sheet.autoSizeColumn(i);
		// }

	}

	/**
	 * 下载周期内总时长报表
	 * 
	 * @param voiceTotalTimeStatisticsVo
	 *            周期内总时长统计数据对象
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void downloadVoiceTotalTimeStatistics(VoiceTotalTimeStatisticsVo voiceTotalTimeStatisticsVo, HttpServletResponse response) throws UnsupportedEncodingException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		this.appendSheetInfoForVoiceTotalTimeStatistics(workbook, workbook.createSheet("总计"), voiceTotalTimeStatisticsVo.getTitles(), voiceTotalTimeStatisticsVo.getReports());

		String downFileName = "语音报表-" + new DateTime().toString("yyyyMMddHHmm");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(downFileName.getBytes("utf-8"), "iso8859-1") + ".xls");
		response.setContentType("applicationnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		BufferedOutputStream os = null;
		try {
			// 执行写入操作***********************************
			os = new BufferedOutputStream(response.getOutputStream());
			workbook.write(os);
			os.flush();
		} catch (IOException e) {
			_LOGGER.error("[报表导出失败]", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					_LOGGER.error("[报表导出失败--流关闭失败]", e);
				}
			}
		}
	}

	/**
	 * 下载 周期内每日时长 报表
	 * 
	 * @param voiceCycleDateStatisticsVo4Total
	 *            周期内每日时长总统计
	 * @param voiceCycleDateStatisticsVo4Calling
	 *            周期内每日时长呼出统计
	 * @param voiceCycleDateStatisticsVo4Called
	 *            周期内每日时长呼入统计
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void downloadVoiceCycleDateStatistics(VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Total, VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Calling,
			VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Called, HttpServletResponse response) throws UnsupportedEncodingException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		this.appendSheetInfo(workbook, workbook.createSheet("总计"), voiceCycleDateStatisticsVo4Total.getTitles(), voiceCycleDateStatisticsVo4Total.getReports());
		this.appendSheetInfo(workbook, workbook.createSheet("呼出"), voiceCycleDateStatisticsVo4Calling.getTitles(), voiceCycleDateStatisticsVo4Calling.getReports());
		this.appendSheetInfo(workbook, workbook.createSheet("呼入"), voiceCycleDateStatisticsVo4Called.getTitles(), voiceCycleDateStatisticsVo4Called.getReports());

		String downFileName = "语音报表-" + new DateTime().toString("yyyyMMddHHmm");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(downFileName.getBytes("utf-8"), "iso8859-1") + ".xls");
		response.setContentType("applicationnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		BufferedOutputStream os = null;
		try {
			// 执行写入操作***********************************
			os = new BufferedOutputStream(response.getOutputStream());
			workbook.write(os);
			os.flush();
		} catch (IOException e) {
			_LOGGER.error("[报表导出失败]", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					_LOGGER.error("[报表导出失败--流关闭失败]", e);
				}
			}
		}
	}

	/**
	 * 下载 指定时间段统计 报表
	 * 
	 * @param voiceSpecifiedDateStatisticsVo4Total
	 *            时间段总统计
	 * @param voiceSpecifiedDateStatisticsVo4Calling
	 *            时间段呼出统计
	 * @param voiceSpecifiedDateStatisticsVo4Called
	 *            时间段呼入统计
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void downloadVoiceSpecifiedDateStatistics(VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVo4Total, VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVo4Calling,
			VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVo4Called, HttpServletResponse response) throws UnsupportedEncodingException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		this.appendSheetInfo(workbook, workbook.createSheet("总计"), voiceSpecifiedDateStatisticsVo4Total.getTitles(), voiceSpecifiedDateStatisticsVo4Total.getReports());
		this.appendSheetInfo(workbook, workbook.createSheet("呼出"), voiceSpecifiedDateStatisticsVo4Calling.getTitles(), voiceSpecifiedDateStatisticsVo4Calling.getReports());
		this.appendSheetInfo(workbook, workbook.createSheet("呼入"), voiceSpecifiedDateStatisticsVo4Called.getTitles(), voiceSpecifiedDateStatisticsVo4Called.getReports());

		String downFileName = "语音报表-" + new DateTime().toString("yyyyMMddHHmm");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(downFileName.getBytes("utf-8"), "iso8859-1") + ".xls");
		response.setContentType("applicationnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		BufferedOutputStream os = null;
		try {
			// 执行写入操作***********************************
			os = new BufferedOutputStream(response.getOutputStream());
			workbook.write(os);
			os.flush();
		} catch (IOException e) {
			_LOGGER.error("[报表导出失败]", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					_LOGGER.error("[报表导出失败--流关闭失败]", e);
				}
			}
		}
	}

	private void appendSheetInfo(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titles, List<List<Object>> reports) {
		HSSFRow firstRow = sheet.createRow((int) 1);

		CellStyle firstCellStyle = workbook.createCellStyle();// 首行
		firstCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		firstCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		firstCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		firstCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle contentCellStyle = workbook.createCellStyle();// 内容
		contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyle.setWrapText(true);
		contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle contentCellStyleTop = workbook.createCellStyle();// 内容顶部

		// HSSFPalette customPalette = workbook.getCustomPalette();//自定义颜色
		// 索引只能是 8 ~ 64
		// customPalette.setColorAtIndex((short) 13, (byte) 224, (byte) 238,
		// (byte) 238);
		// contentCellStyleTop.setFillForegroundColor((short) 13);
		// contentCellStyleTop.setFillPattern(CellStyle.SOLID_FOREGROUND);
		contentCellStyleTop.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyleTop.setWrapText(true);
		contentCellStyleTop.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		Font firstFont = workbook.createFont();// 首行文字
		firstFont.setFontName("华文中宋");// 设置字体名称
		firstFont.setFontHeightInPoints((short) 11);// 设置字号

		Font contentFont = workbook.createFont();// 内容文字
		firstFont.setFontName("华文中宋");// 设置字体名称
		firstFont.setFontHeightInPoints((short) 10);// 设置字号

		firstCellStyle.setFont(firstFont);
		contentCellStyle.setFont(contentFont);

		for (int i = 0; i < titles.size(); i++) {
			HSSFCell curCell = firstRow.createCell((short) i);
			curCell.setCellValue(titles.get(i));
			curCell.setCellType(Cell.CELL_TYPE_STRING);
			curCell.setCellStyle(firstCellStyle);
		}

		for (int i = 0; i < reports.size(); i++) {
			HSSFRow curRow = sheet.createRow((short) (i + 2));
			List<Object> curReport = reports.get(i);
			for (int j = 0; j < curReport.size(); j++) {
				HSSFCell curCell = curRow.createCell((short) j);
				if (curReport.get(j) instanceof Number) {
					// 采取分钟制
					curCell.setCellValue(Double.valueOf(this.getShowInfo(Integer.valueOf(String.valueOf(curReport.get(j)))).replace("分", "")));
					curCell.setCellType(Cell.CELL_TYPE_NUMERIC);
				} else {
					curCell.setCellValue(String.valueOf(curReport.get(j)));
					curCell.setCellType(Cell.CELL_TYPE_STRING);
				}
				if (i < 2) {
					curCell.setCellStyle(contentCellStyleTop);
				} else {
					curCell.setCellStyle(contentCellStyle);
				}
			}
			// curRow.setHeightInPoints((6 *
			// sheet.getDefaultRowHeightInPoints()));//设置行高度
		}
		// for (int i = 0; i < voiceLogReport2Model.getTitles().size(); i++)
		// {//自动调整列宽
		// sheet.autoSizeColumn(i);
		// }

	}

	private void appendSheetInfoForVoiceTotalTimeStatistics(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titles, List<List<Object>> reports) {
		HSSFRow firstRow = sheet.createRow((int) 1);

		CellStyle firstCellStyle = workbook.createCellStyle();// 首行
		firstCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		firstCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		firstCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		firstCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle contentCellStyle = workbook.createCellStyle();// 内容
		contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyle.setWrapText(true);
		contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle contentCellStyleTop = workbook.createCellStyle();// 内容顶部

		// HSSFPalette customPalette = workbook.getCustomPalette();//自定义颜色
		// 索引只能是 8 ~ 64
		// customPalette.setColorAtIndex((short) 13, (byte) 224, (byte) 238,
		// (byte) 238);
		// contentCellStyleTop.setFillForegroundColor((short) 13);
		// contentCellStyleTop.setFillPattern(CellStyle.SOLID_FOREGROUND);
		contentCellStyleTop.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyleTop.setWrapText(true);
		contentCellStyleTop.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		Font firstFont = workbook.createFont();// 首行文字
		firstFont.setFontName("华文中宋");// 设置字体名称
		firstFont.setFontHeightInPoints((short) 11);// 设置字号

		Font contentFont = workbook.createFont();// 内容文字
		firstFont.setFontName("华文中宋");// 设置字体名称
		firstFont.setFontHeightInPoints((short) 10);// 设置字号

		firstCellStyle.setFont(firstFont);
		contentCellStyle.setFont(contentFont);

		for (int i = 0; i < titles.size(); i++) {
			HSSFCell curCell = firstRow.createCell((short) i);
			curCell.setCellValue(titles.get(i));
			curCell.setCellType(Cell.CELL_TYPE_STRING);
			curCell.setCellStyle(firstCellStyle);
		}

		for (int i = 0; i < reports.size(); i++) {
			HSSFRow curRow = sheet.createRow((short) (i + 2));
			List<Object> curReport = reports.get(i);
			for (int j = 0; j < curReport.size(); j++) {
				HSSFCell curCell = curRow.createCell((short) j);
				if (curReport.get(j) instanceof Number) {
					if (j == 3 || j == 6) {
						curCell.setCellValue(Double.valueOf(String.valueOf(curReport.get(j))));
					} else {
						curCell.setCellValue(Double.valueOf(this.getShowInfo(Integer.valueOf(String.valueOf(curReport.get(j)))).replace("分", "")));
					}
					// 采取分钟制
					curCell.setCellType(Cell.CELL_TYPE_NUMERIC);
				} else {
					curCell.setCellValue(String.valueOf(curReport.get(j)));
					curCell.setCellType(Cell.CELL_TYPE_STRING);
				}
				if (i < 2) {
					curCell.setCellStyle(contentCellStyleTop);
				} else {
					curCell.setCellStyle(contentCellStyle);
				}
			}
			// curRow.setHeightInPoints((6 *
			// sheet.getDefaultRowHeightInPoints()));//设置行高度
		}
		// for (int i = 0; i < voiceLogReport2Model.getTitles().size(); i++)
		// {//自动调整列宽
		// sheet.autoSizeColumn(i);
		// }

	}

	private String getShowInfo(int duration) {
		StringBuilder durationShow = new StringBuilder();
		if (duration > 0) {
			// 时分秒
			// int hour = duration / 3600;
			// int min = duration % 3600 / 60;
			// int sec = duration % 60;
			// durationShow.append(hour).append("时").append(min).append("分").append(sec).append("秒");

			int min = duration / 60;
			BigDecimal result = new BigDecimal(min + (duration % 60 / 60D));
			durationShow.append(result.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()).append("分");
		} else {
			durationShow.append("0分");
		}
		return durationShow.toString();
	}
}
