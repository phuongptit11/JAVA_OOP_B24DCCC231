import java.util.Scanner;

public class SinhVienApp {
    static class SinhVien {
        private String maSV;
        private String hoTen;
        private int tuoi;
        private static int dem = 0;

        public SinhVien(String maSV, String hoTen, int tuoi) {
            this.maSV = maSV;
            this.hoTen = hoTen;
            this.tuoi = tuoi;
            dem++;
        }

        public void hienThi() {
            System.out.println("Mã SV: " + maSV + ", Họ tên: " + hoTen + ", Tuổi: " + tuoi);
        }

        public static void hienThiTongSV() {
            System.out.println("Tổng số sinh viên đã tạo: " + dem);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng sinh viên: ");
        int n = Integer.parseInt(sc.nextLine());

        SinhVien[] danhSach = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1));
            System.out.print("Mã SV: ");
            String ma = sc.nextLine();
            System.out.print("Họ tên: ");
            String ten = sc.nextLine();
            System.out.print("Tuổi: ");
            int tuoi = Integer.parseInt(sc.nextLine());

            danhSach[i] = new SinhVien(ma, ten, tuoi);
        }

        System.out.println("\n📋 Danh sách sinh viên:");
        for (SinhVien sv : danhSach) {
            sv.hienThi();
        }

        SinhVien.hienThiTongSV();
        sc.close();
    }
}
