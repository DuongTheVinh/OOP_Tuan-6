package ThucPham;

import java.time.LocalDate;

public class HangThucPham implements Comparable<HangThucPham> {

    private final String maHang;
    private String tenHang;
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private float gia;
    private int id;

    public static int demSoLuong = 0;

    // Constructor mặc định
    public HangThucPham() {
        this.id = ++demSoLuong;
        this.maHang = "MACDINH";
        this.tenHang = "Hàng Mặc Định";
        this.ngaySanXuat = LocalDate.now();
        this.ngayHetHan = LocalDate.now().plusDays(7);
        this.gia = 1.0f;
    }

    // Constructor chuẩn 5 tham số (khớp với Main.java)
    public HangThucPham(String maHang, String tenHang, LocalDate ngaySanXuat, LocalDate ngayHetHan, float gia) {
        this.id = ++demSoLuong;
        this.maHang = (maHang == null || maHang.trim().isEmpty()) ? "TP" + id : maHang;
        setTenHang(tenHang);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        setGia(gia);
    }

    public boolean kiemTraNgaySX(LocalDate ngaySX) {
        return ngaySX != null && !ngaySX.isAfter(LocalDate.now());
    }

    public boolean kiemTraNgayHetHan(LocalDate ngayHetHan, LocalDate ngaySX) {
        return ngaySX != null && ngayHetHan != null && !ngayHetHan.isBefore(ngaySX);
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        if (tenHang == null || tenHang.trim().isEmpty())
            throw new IllegalArgumentException("Tên hàng không được rỗng!");
        this.tenHang = tenHang;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        if (gia <= 0)
            throw new IllegalArgumentException("Giá phải > 0!");
        this.gia = gia;
    }

    public static int getDemSoLuong() {
        return demSoLuong;
    }

    public static void setDemSoLuong(int demSoLuong) {
        HangThucPham.demSoLuong = demSoLuong;
    }

    public String getMaHang() {
        return maHang;
    }

    public boolean kiemTraNgayHetHan() {
        return LocalDate.now().isAfter(ngayHetHan);
    }

    public String trangThaiChiTiet() {
        LocalDate homNay = LocalDate.now();
        if (homNay.isBefore(ngaySanXuat))
            return "Chưa sản xuất";
        if (homNay.isAfter(ngayHetHan))
            return "Hết hạn";
        if (homNay.isEqual(ngayHetHan))
            return "Hết hạn hôm nay";
        long con = ngayHetHan.toEpochDay() - homNay.toEpochDay();
        return "Còn " + con + " ngày";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Không thể sao chép hàng thực phẩm");
    }

    @Override
    public int compareTo(HangThucPham o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return String.format("|%-6d|%-10s|%-20s|%-12s|%-12s|%,-12.0f|", 
                id, maHang, tenHang, ngaySanXuat, ngayHetHan, gia);
    }
}