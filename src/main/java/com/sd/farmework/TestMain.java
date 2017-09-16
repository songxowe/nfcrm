package com.sd.farmework;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sd.farmework.common.TreeService;

public class TestMain {
	/**
	 * @param args
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static void main1(String[] args) throws UnknownHostException,
			SocketException {
		// TODO Auto-generated method stub
		// 修改测试一下
		// 得到IP，输出PC-201309011313/122.206.73.83
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia);
		getLocalMac(ia);
		// 123

	}

	private static void getLocalMac(InetAddress ia) throws SocketException {
		// TODO Auto-generated method stub
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.out.println("mac数组长度：" + mac.length);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			System.out.println("每8位:" + str);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		System.out.println("本机MAC地址:" + sb.toString().toUpperCase());
	}

	// public static void main(String[] args) throws SQLException,
	// ClassNotFoundException {
	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	// Connection conn = DriverManager.getConnection(
	// "jdbc:://192.168.1.74:1433;DatabaseName=frameworkdb",
	// "sa", "shida123456");
	//
	// Class.forName("com.mysql.jdbc.Driver");
	// Connection conn = DriverManager.getConnection(
	// "jdbc:mysql://192.168.1.241:3306/crmmanagerdb", "root",
	// "shida123456");
	//
	// System.out.println(conn);
	//
	// PreparedStatement pre = conn
	// .prepareStatement("select * from sys_function_info_power");
	//
	// ResultSet rs = pre.executeQuery();
	// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// while (rs.next()) {
	// Map<String, Object> map = new HashMap();
	// map.put("PARENT_ID", rs.getString("parent_function_id"));
	// map.put("ID", rs.getString("funciton_id"));
	// map.put("NAME", rs.getString("function_name"));
	// map.put("isVirtual", 0);
	// list.add(map);
	// }
	// rs.close();
	// pre.close();
	// conn.close();
	// TreeService s = new TreeService();
	// System.out.println(list.size());
	// System.out.println(s.getTreeData(list, "0"));
	//
	// String srt = JSONAr ray.fromObject(s.getTreeData(list, "0")).toString();
	// System.out.println(srt);
	//
	// }
}