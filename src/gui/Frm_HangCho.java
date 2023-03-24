package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.ChatLieu;
import entity.KhachHang;
import entity.MauSac;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.SanPham;
import entity.TacGia;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;
import entity.XuatXu;
import service.impl.ChatLieuServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhaCungCapServiceImpl;
import service.impl.NhaXuatBanServiceImpl;
import service.impl.SanPhamServiceImpl;
import service.impl.TacGiaServiceImpl;
import service.impl.TheLoaiServiceImpl;
import service.impl.XuatXuServiceImpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Frm_HangCho extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelKhachHang;
	private JTable tableKhachHang;
	private JScrollPane scrollKhachHang;
	private JButton btnThanhToan;
	private String loaiSanPham;
	private KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();
	private JLabel lblTitle;
	private JButton btnThoat;
	private ButtonGroup group;
	private JButton btnXoa;
	Pn_TaoHoaDon pn_TaoHoaDon ;
	ArrayList<KhachHang> listKhachHang;
	public static boolean xoa = false;
	public static boolean thanhToan = false;
	public HashMap<String, ArrayList<SanPham>> listHoaDonCho = new HashMap<String, ArrayList<SanPham>>();
	private String sdt = "";
	private ShareData shareData;

	public Frm_HangCho(HashMap<String, ArrayList<SanPham>> listHoaDonCho, ShareData data){
		this.shareData = data;
		this.listHoaDonCho = listHoaDonCho;
		setResizable(false);
		setSize(630, 600);
		
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblTitle = new JLabel("HÓA ĐƠN CHỜ");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 6, 586, 28);
		getContentPane().add(lblTitle);
		String[] cols = { "Tên khách hàng", "Số điện thoại khách hàng" };
		modelKhachHang = new DefaultTableModel(cols, 0);
		tableKhachHang = new JTable(modelKhachHang);
		tableKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableKhachHang.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 17));
		scrollKhachHang = new JScrollPane(tableKhachHang);
		scrollKhachHang.setBounds(10, 117, 596, 436);

		tableKhachHang.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableKhachHang.setAutoCreateRowSorter(true);
		tableKhachHang.setRowHeight(25);
//		tableKhachHang.setBackground(Color.decode("#BEFFC0"));
		scrollKhachHang.getViewport().setBackground(Color.WHITE);
		tableKhachHang.getTableHeader().setPreferredSize(new Dimension(0, 40));
		getContentPane().add(scrollKhachHang);

		btnThanhToan = new JButton("Thanh toán tiếp");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setBounds(82, 61, 207, 28);
		getContentPane().add(btnThanhToan);
		group = new ButtonGroup();

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBounds(508, 10, 98, 28);
		getContentPane().add(btnThoat);
		
		btnXoa = new JButton("Xóa hàng chờ");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(329, 61, 207, 28);
		getContentPane().add(btnXoa);
		loadDuLieu();
		
		btnXoa.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThoat.addActionListener(this);
		tableKhachHang.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThanhToan)) {
			thanhToan();
		}
		if(o.equals(btnThoat)) {
			Thoat();
		}
		if(o.equals(btnXoa)) {
			xoaHoaDon();
		}
		
	}

	public HashMap<String, ArrayList<SanPham>> getListHoaDonCho() {
		return listHoaDonCho;
	}
	
	public void setListHoaDonCho(HashMap<String, ArrayList<SanPham>> listHoaDon) {
		listHoaDonCho = listHoaDon;
		
	}
	public void loadDuLieu() {
		listKhachHang = new ArrayList<>();
		for (String sdtKH : listHoaDonCho.keySet()) {
			KhachHang khachHang = null;
			try {
				khachHang = khachHangServiceImpl.timKhachHangBangSDT(sdtKH);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listKhachHang.add(khachHang);
		}
		
		for (KhachHang khachHang : listKhachHang) {
			Object [] o = {khachHang.getHoTenKhachHang(),khachHang.getsDT()};
			modelKhachHang.addRow(o);
		}
	}
	public void thanhToan() {
		int row = tableKhachHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm thanh toán","Báo lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		sdt = modelKhachHang.getValueAt(row, 1).toString();
		ArrayList<SanPham> listSPThanhToan = listHoaDonCho.get(sdt);
		listHoaDonCho.remove(sdt);
		new Thread(()->{
			synchronized (shareData) {
				shareData.setThanhToan(true);
				shareData.setSdtThanhToan(sdt);
				shareData.setListSanPhamThanhToanTiep(listSPThanhToan);
				shareData.setListHoaDonCho(listHoaDonCho);
				shareData.notifyAll();
				this.setVisible(false);	
			}
		}).start();
	}
	public void xoaHoaDon() {
		int row = tableKhachHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm xóa","Báo lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listHoaDonCho.remove(sdt);
		shareData.setListHoaDonCho(listHoaDonCho);
		modelKhachHang.removeRow(row);
	}
	public void Thoat() {
		new Thread(()->{
			synchronized (shareData) {
				shareData.notifyAll();
				this.setVisible(false);
			}
		}).start();
	}
}
