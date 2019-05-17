package Controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.Model;
import View.viewLogin;
import View.viewMenuAdmin;
import View.viewMenuCustomer;
import View.viewMenuManager;
import View.viewRegist;
import View.viewMC_Setting;
import View.viewMC_Parkir;
import View.viewMA_Pembayaran1;
import View.viewMA_Pembayaran2;
import View.viewMA_BuktiPembayaran;
import View.viewMA_Laporan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ZakkiFarhan
 */
public class Controller implements ActionListener{
    private View.viewLogin viewLogin; 
    private View.viewMenuAdmin viewMenuAdmin;
    private View.viewMenuCustomer viewMenuCustomer;
    private View.viewRegist viewRegist; 
    private View.viewMC_Setting viewMC_Setting;
    private View.viewMC_Parkir viewMC_Parkir;
    private View.viewMA_Pembayaran1 viewMAPembayaran1;
    private View.viewMA_Pembayaran2 viewMAPembayaran2;
    private View.viewMA_BuktiPembayaran viewMABuktiPembayaran;
    private View.viewMA_Laporan viewMA_Laporan;
    private View.viewMenuManager viewMenuManager;
        
    int status;
    Model model;  
    DefaultTableModel mod;
    
    public Controller(Model m){          
        viewLogin = new viewLogin();
        viewLogin.setVisible(true);
        viewLogin.addActionListener(this);
        
        viewMenuCustomer = new viewMenuCustomer();
        viewMenuCustomer.setVisible(false);
        viewMenuCustomer.addActionListener(this);               
        
        viewRegist = new viewRegist(); 
        viewRegist.setVisible(false);
        viewRegist.addActionListener(this);
        
        viewMC_Setting = new viewMC_Setting();
        viewMC_Setting.setVisible(false);
        viewMC_Setting.addActionListener(this);
        
        viewMC_Parkir = new viewMC_Parkir();
        viewMC_Parkir.setVisible(false);
        viewMC_Parkir.addActionListener(this);
        
        viewMenuAdmin = new viewMenuAdmin();
        viewMenuAdmin.setVisible(false);
        viewMenuAdmin.addActionListener(this);
        
        viewMAPembayaran1 = new viewMA_Pembayaran1();
        viewMAPembayaran1.setVisible(false);
        viewMAPembayaran1.addActionListener(this);
        
        viewMAPembayaran2 = new viewMA_Pembayaran2();
        viewMAPembayaran2.setVisible(false);
        viewMAPembayaran2.addActionListener(this);
        
        viewMABuktiPembayaran = new viewMA_BuktiPembayaran();
        viewMABuktiPembayaran.setVisible(false);
        viewMABuktiPembayaran.addActionListener(this);   
        
        viewMA_Laporan = new viewMA_Laporan();
        viewMA_Laporan.setVisible(false);
        viewMA_Laporan.addActionListener(this);
        
        viewMenuManager = new viewMenuManager();
        viewMenuManager.setVisible(false);
        viewMenuManager.addActionListener(this);
                                                              
        this.model = m; 
    }    
    
    public void Login(String a, String b){                         
        if (model.isValidLogin(a, b)){                        
            switch (model.getStatus()) {
                case 1:
                    viewMenuAdmin.setVisible(true);
                    break;
                case 2:
                    viewLogin.setVisible(false);
                    viewMenuCustomer.setVisible(true);
                    break;
                case 3:
                    viewMenuManager.setVisible(true);
                    break;
                default:
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(viewLogin, "Username atau Password Salah");
        }                    
    }    
    public void Registration(String uname,String pass, String retype,boolean isCheck){          
        if (isCheck){                    
            if (uname.length() < 20 && uname.length()>5){
                if (pass.length() < 20 && pass.length()>5){
                    if (pass.equals(retype)){
                        if (model.isValidRegistration(uname, pass)){
                            JOptionPane.showMessageDialog(null, "Pendaftaran Berhasil");                        
                        }else{
                            JOptionPane.showMessageDialog(null, "Pendaftaran Gagal");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Password Salah");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Password tidak boleh kurang dari 5 digit & lebih dari 20 digit");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Username tidak boleh kurang dari 5 digit & lebih dari 20 digit");
            }                
        }else{
            JOptionPane.showMessageDialog(null, "Anda harus membaca agreement terlebih dahulu");
        }
    }         
    public void customerSetting(String oldPass, String newPass, String newRePass){
        if(model.isValidOldPass(oldPass)){
            if (newPass.length() < 20 && newPass.length()>5){
                if (newPass.equals(newRePass)){
                    if (model.isValidNewPass(newPass)){
                        JOptionPane.showMessageDialog(null, "Update Berhasil");                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Update Gagal");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Password Salah");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Password tidak boleh kurang dari 5 digit & lebih dari 20 digit");
            }
        }else{
            
        }
    }    
    public void Parkir(int id_akun,String plat,int vip,String loc, Date tanggal, Date jam_masuk){  
        //isDuplicatePlat
        
        if (model.isValidParking(id_akun, plat,vip,loc, tanggal, jam_masuk)){
            JOptionPane.showMessageDialog(null, "Parkir Berhasil");                        
        }else{
            JOptionPane.showMessageDialog(null, "Parkir Gagal");                                    
        }        
    }    
    public void searchPlat(String plat){
        if(model.isValidPlat(plat)){
            JOptionPane.showMessageDialog(null, "Plat Nomor Ditemukan");
            viewMAPembayaran2.setVisible(true);
            viewMAPembayaran2.setJam_m(model.getTime());
            viewMAPembayaran1.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Plat Nomor Tidak Ditemukan");                                    
        }
    }
    public long Calculate(Date jam_m, Date jam_k){
        Date d1 = jam_m;
        Date d2 = jam_k;
        long diffMs = d1.getTime() - d2.getTime();
        long diffSec = diffMs / 1000;
        long min = diffSec / 60;
        long sec = diffSec % 60;        
                
        return min*50*-1;        
    }
    public void Pembayaran(String x,Date jam_k){
        int y = Integer.parseInt(x);
        model.isValidPembayaran(y,jam_k);
    }
    public void searchPlatBukti(String plat){
        if(model.isValidPlat(plat)){
            JOptionPane.showMessageDialog(null, "Plat Nomor Ditemukan");            
            viewMABuktiPembayaran.setTxt_totB(model.total_harga(plat));
            viewMABuktiPembayaran.setTxt_totM(model.total_menit(plat));            
        }else{
            JOptionPane.showMessageDialog(null, "Plat Nomor Tidak Ditemukan");                                    
        }
    }
    public void Laporan(){
        this.mod = new DefaultTableModel();                
        viewMA_Laporan.getTableData().setModel(mod);        
        
        mod.addColumn("id_parkir");
        mod.addColumn("id_akun");
        mod.addColumn("plat");
        mod.addColumn("vip");
        mod.addColumn("lokasi");
        mod.addColumn("date");
        mod.addColumn("jam_masuk");
        mod.addColumn("jam_keluar");
        mod.addColumn("total_harga");
        
        model.getParkirData(mod);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(viewLogin.getBt_login())){            
            Login(viewLogin.getTxt_name(), viewLogin.getTxt_pass());
        }else if(source.equals(viewLogin.getBt_regist())){            
            viewLogin.setVisible(false);                          
            viewRegist.setVisible(true);                   
        }else if(source.equals(viewRegist.getBtn_daftar())){
            Registration(viewRegist.getTxt_name(),viewRegist.getTxt_pass(),viewRegist.getTxt_repass(),viewRegist.getCheck());            
        }else if(source.equals(viewMenuCustomer.getBtn_logout())){
            viewMenuCustomer.setVisible(false);
            Controller c = new Controller(model);
        }else if(source.equals(viewMenuCustomer.getBtn_setting())){
            viewMenuCustomer.setVisible(false);
            viewMC_Setting.setVisible(true);
        }else if(source.equals(viewMenuCustomer.getBtn_parkir())){
            viewMenuCustomer.setVisible(false);
            viewMC_Parkir.setVisible(true);
        }else if(source.equals(viewMC_Parkir.getBtnParkir())){
            Parkir(model.getID(), viewMC_Parkir.getPlat(),viewMC_Parkir.getVIP(),viewMC_Parkir.getLoc(), viewMC_Parkir.getDate(), viewMC_Parkir.getTime());            
            System.out.println(viewMC_Parkir.getLoc());
        }else if(source.equals(viewMC_Setting.getBtn_submit())){
            customerSetting(viewMC_Setting.getTxt_oldpass(),viewMC_Setting.getTxt_newpass(), viewMC_Setting.getTxt_newrepass());
        }else if(source.equals(viewMC_Setting.getBtn_back())){
            viewMC_Setting.setVisible(false);
            viewMenuCustomer.setVisible(true);
        }else if(source.equals(viewMenuAdmin.getBtn_pembayaran())){
            viewMenuAdmin.setVisible(false);
            viewMAPembayaran1.setVisible(true);
        }else if(source.equals(viewMAPembayaran1.getBtn_search())){            
            searchPlat(viewMAPembayaran1.getText_searchplat());
        }else if(source.equals(viewMAPembayaran2.getBtn_cal())){            
            viewMAPembayaran2.setTotal(Calculate(viewMAPembayaran2.getTimeMasuk(),viewMAPembayaran2.getTimeKeluar()));
        }else if (source.equals(viewMAPembayaran2.getBtn_submit())){
            Pembayaran(viewMAPembayaran2.getT_total(),viewMAPembayaran2.getTimeKeluar());
        }else if(source.equals(viewMABuktiPembayaran.getBtn_cari())){            
            searchPlatBukti(viewMABuktiPembayaran.getT_search());
        }else if(source.equals(viewMenuAdmin.getBtn_bukti())){            
            viewMenuAdmin.setVisible(false);
            viewMABuktiPembayaran.setVisible(true);
        }else if(source.equals(viewMenuAdmin.getBtn_laporan())){            
            viewMenuAdmin.setVisible(false);            
            viewMA_Laporan.setVisible(true);
            Laporan();
        }else if(source.equals(viewMenuManager.getBtn_lapo())){
            viewMenuManager.setVisible(false);            
            viewMA_Laporan.setVisible(true);
            Laporan();
        }
    }
}