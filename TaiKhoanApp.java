import java.util.*;

public class TaiKhoanApp {
    static class TaiKhoan {
        private String soTK;
        private String tenChuTK;
        private double soDu;

        public TaiKhoan(String soTK, String tenChuTK, double soDu) {
            this.soTK = soTK;
            this.tenChuTK = tenChuTK;
            this.soDu = soDu;
        }

        public void hienThi() {
            System.out.printf("Số TK: %s | Chủ TK: %s | Số dư: %.2f\n", soTK, tenChuTK, soDu);
        }

        public void napTien(double tien) {
            if (tien > 0) soDu += tien;
        }

        public void rutTien(double tien) {
            if (tien > 0 && tien <= soDu) soDu -= tien;
        }

        public void tinhLai(double laiSuat) {
            soDu += soDu * laiSuat / 100;
        }

        public double getSoDu() {
            return soDu;
        }

        public String getSoTK() {
            return soTK;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<TaiKhoan> danhSach = new ArrayList<>();

        System.out.print("Nhập số lượng tài khoản: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin tài khoản thứ " + (i + 1));
            System.out.print("Số TK: ");
            String soTK = sc.nextLine();
            System.out.print("Tên chủ TK: ");
            String ten = sc.nextLine();
            System.out.print("Số dư ban đầu: ");
            double soDu = Double.parseDouble(sc.nextLine());

            TaiKhoan tk = new TaiKhoan(soTK, ten, soDu);
            danhSach.add(tk);
        }

        // Giao dịch mẫu
        danhSach.get(0).napTien(500000);
        danhSach.get(1).rutTien(200000);
        danhSach.get(2).tinhLai(5);

        System.out.println("\n📋 Danh sách tài khoản sau giao dịch:");
        for (TaiKhoan tk : danhSach) {
            tk.hienThi();
        }

        // Sắp xếp theo số dư giảm dần
        danhSach.sort((a, b) -> Double.compare(b.getSoDu(), a.getSoDu()));
        System.out.println("\n📊 Sắp xếp theo số dư giảm dần:");
        for (TaiKhoan tk : danhSach) {
            tk.hienThi();
        }

        sc.close();
    }
}
