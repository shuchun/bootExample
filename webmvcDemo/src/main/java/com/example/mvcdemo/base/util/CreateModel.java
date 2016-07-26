package com.example.mvcdemo.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 * 根据数据库生成Model类
 * 
 */
public class CreateModel {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		String tableName = "PC_SHOP";// 表名
		String createName = "yxy";// 作者名
		String info = "商户安全验证表";// 表说明
		String packageName = "paymentOnline";// 模块名

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.250:1521/roshan", "magnus1010", "magnus1010");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from " + tableName);
			int colNum = rs.getMetaData().getColumnCount();
			File file = new File("src/main/java/com/lesaas/slardar/" + packageName
					+ "/entity/" + ToolUtils.getClassName(tableName) + ".java");
			 
			Writer fw = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
			fw.write("/**\r\n");
			fw.write(" * Tech Ltd.\r\n");
			fw.write(" */\r\n");
			fw.write("package com.lesaas.slardar." + packageName + ".entity;\r\n\r\n");
			for (int i = 1; i <= colNum; i++) {
				int columnType = rs.getMetaData().getColumnType(i);
				if (columnType == java.sql.Types.DATE) {
					fw.write("import java.util.Date;\r\n\r\n");
					break;
				}
			}
			fw.write("import javax.persistence.Entity;\r\n");
			fw.write("import javax.persistence.Column;\r\n");
			fw.write("import javax.persistence.GeneratedValue;\r\n");
			fw.write("import javax.persistence.Id;\r\n");
			fw.write("import javax.persistence.Table;\r\n\r\n");
			fw.write("import org.hibernate.annotations.GenericGenerator;\r\n\r\n");
			// fw.write("import org.hibernate.annotations.Type;\r\n\r\n");
			fw.write("/**\r\n");
			fw.write(" * @see(功能介绍): " + info + "信息\r\n");
			fw.write(" * @date(创建日期): "
					+ ToolUtils.formatDate(new Date(), "yyyy-MM-dd"));
			fw.write("\r\n * @author(创建人): " + createName);
			fw.write("\r\n */\r\n");
			fw.write("\r\n@Entity\r\n");
			fw.write("@Table(name = \"" + tableName + "\")\r\n");
			fw.write("public class " + ToolUtils.getClassName(tableName)
					+ " {\r\n");
			for (int i = 1; i <= colNum; i++) {
				String type = "String";
				int columnType = rs.getMetaData().getColumnType(i);
				if (columnType == java.sql.Types.DATE) {
					type = "Date";
				} else if (columnType == java.sql.Types.NUMERIC) {
					if (rs.getMetaData().getPrecision(i) == 38) {
						type = "Integer";
					} else {
						type = "Double";
					}
				}
				fw.write("\r\n	/*  */\r\n	");
				fw.write("private "
						+ type
						+ " "
						+ ToolUtils.getHorizontalLineUpper(rs.getMetaData()
								.getColumnName(i).toLowerCase()) + ";\r\n");
			}
			fw.write("\r\n\t@Id\r\n");
			fw
					.write("\t@GeneratedValue(generator = \"paymentableGenerator\")  \r\n");
			fw
					.write("\t@GenericGenerator(name = \"paymentableGenerator\", strategy = \"assigned\")\r\n");
			for (int i = 1; i <= colNum; i++) {
				String type = "String";
				int columnType = rs.getMetaData().getColumnType(i);
				if (columnType == java.sql.Types.DATE) {
					type = "Date";
				} else if (columnType == java.sql.Types.NUMERIC) {
					if (rs.getMetaData().getPrecision(i) == 38) {
						type = "Integer";
					} else {
						type = "Double";
					}
				}
				String columnName = rs.getMetaData().getColumnName(i)
						.toLowerCase();
				String field = ToolUtils.getHorizontalLineUpper(columnName);
				if (i != 1) {
					fw.write("");
				}
				fw.write("	@Column(name=\"" + columnName + "\")\r\n");
				fw.write("	public " + type + " get"
						+ ToolUtils.getFirstUpper(field) + "() {\r\n");
				fw.write("		return " + field + ";\r\n");
				fw.write("	} \r\n");
				fw.write("\r\n	public void set"
						+ ToolUtils.getFirstUpper(field) + "(" + type + " "
						+ field + ") {\r\n");
				fw.write("		this." + field + "=" + field + ";\r\n");
				fw.write("	} \r\n\r\n");
			}
			fw.write("} ");
			fw.flush();
			fw.close();
			rs.close();
			System.out.println(tableName + ":生产成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
