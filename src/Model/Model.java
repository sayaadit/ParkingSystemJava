/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
 
import Controller.Controller;
import View.viewRegist;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZakkiFarhan
 */
public class Model {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    int status;
    int id;
    int id_p;
    Time jam_m;
    
    public Model(){
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }       
    public boolean isValidRegistration(String uname,String pass){ 
        //BELUM ADA VALIDASI JIKA USERNAME SUDAH TERPAKAI
        
        boolean Value = false;           
        try{            
            sql = "INSERT INTO AKUN VALUES ('"+getJumlahData("akun")+"','"+uname+"','"+pass+"',2)";
            stat.executeUpdate(sql);            
            Value = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Penyimpanan Gagal, "+e.getMessage());
        }
        return Value;
    }
    public boolean isValidLogin(String a, String b){   
        boolean Value = false;
        try {
            sql = "SELECT * FROM akun WHERE username='"+a+"' AND password='"+b+"'";
            rs  = stat.executeQuery(sql);
            if(rs.next()){
                if(a.equals(rs.getString("username")) && b.equals(rs.getString("password"))){                   
                    this.status = rs.getInt("status");
                    this.id = rs.getInt("id");
                    Value = true;
                }
            }else{                    
                Value = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        }
        return Value;
    }
    public boolean isValidOldPass(String a){
        boolean Value = false;
        try {
            sql = "SELECT * FROM akun WHERE id="+this.getID();
            rs  = stat.executeQuery(sql);
            if(rs.next()){
                if(a.equals(rs.getString("password"))){                   
                    Value = true;
                }
            }else{                    
                Value = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        }
        return Value;
    }
    public boolean isValidNewPass(String a){
        boolean Value = false;
        try {
            sql = "UPDATE akun SET password='"+a+"' where id="+this.getID();
            stat.executeUpdate(sql);   
            Value = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        }
        return Value;
    }
    public boolean isValidParking(int id_akun,String plat,int vip,String loc, Date tanggal, Date jam_masuk){
        boolean Value = false;
        
        java.sql.Date myDate=new java.sql.Date(tanggal.getTime());
        java.sql.Time myTime=new java.sql.Time(jam_masuk.getTime());
            
        try{
            sql = "INSERT INTO parkir VALUES ('"+getJumlahData("parkir")+"','"+id_akun+"','"+plat+"','"+vip+"','"+loc+"','"+myDate+"','"+myTime+"','',0)";
            stat.executeUpdate(sql);                        
            Value = true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        }
        return Value;
    }
    public boolean isValidPlat(String plat){
        boolean value = false;
        try {
            sql = "SELECT * FROM parkir WHERE plat='"+plat+"'";
            rs  = stat.executeQuery(sql);
            if(rs.next()){
                if(plat.equals(rs.getString("plat"))){  
                    this.jam_m = rs.getTime("jam_masuk");
                    this.id_p = rs.getInt("id_parkir");
                    value = true;
                }
            }else{                    
                value = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        }
        return value;
    }    
    public boolean isValidPembayaran(int harga,Date jam_k){
        boolean Value = false;
        
        java.sql.Time myTime=new java.sql.Time(jam_k.getTime());
        
        try {
            sql = "UPDATE parkir SET total_harga='"+harga+"', jam_keluar='"+myTime+"' where id_parkir="+this.getIDParkir();
            stat.executeUpdate(sql);   
            Value = true;
            JOptionPane.showMessageDialog(null,"Pembayaran Sukses");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pembayaran Gagal, "+e.getMessage());
        }
        return Value;
    }    
    public int getJumlahData(String tableName){
        int count = 0;
        try{
            sql = "SELECT * FROM "+tableName;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                count++;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pencarian Data Gagal, "+e.getMessage());
        }
        return count = count +1;
    }
    public long total_menit(String plat){
        Date d1 = null;
        Date d2 = null;                
        
        try {
            sql = "SELECT * FROM parkir WHERE plat='"+plat+"'";
            rs  = stat.executeQuery(sql);
            if(rs.next()){
                if(plat.equals(rs.getString("plat"))){                                          
                    d1 = rs.getTime("jam_masuk");
                    d2 = rs.getTime("jam_keluar");                                        
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        }        
        
        long diffMs = d1.getTime() - d2.getTime();
        long diffSec = diffMs / 1000;
        long min = diffSec / 60;
        long sec = diffSec % 60;
        
        return min*-1;
    }
    public int total_harga(String plat){
        int tot = 0;
        try {
            sql = "SELECT * FROM parkir WHERE plat='"+plat+"'";
            rs  = stat.executeQuery(sql);
            if(rs.next()){
                if(plat.equals(rs.getString("plat"))){                                          
                    tot = rs.getInt("total_harga");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Pencarian Data Gagal, "+e.getMessage());
        } 
        return tot;
    }    
    public void getParkirData(DefaultTableModel mod){                
        try{
            sql = "SELECT * FROM parkir";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                Object[] obj = new Object [9];
                obj[0] = rs.getInt("id_parkir");
                obj[1] = rs.getInt("id_akun");
                obj[2] = rs.getString("plat");
                obj[3] = rs.getInt("vip");
                obj[4] = rs.getString("lokasi");
                obj[5] = rs.getDate("date");
                obj[6] = rs.getTime("jam_masuk");
                obj[7] = rs.getString("jam_keluar");
                obj[8] = rs.getInt("total_harga");
                
                mod.addRow(obj);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pencarian Data Gagal, "+e.getMessage());
        }
    }    
    public Time getTime(){
        return this.jam_m;
    }
    public int getStatus(){
        return this.status;
    }
    public int getID(){
        return this.id;
    }
    public int getIDParkir(){
        return this.id_p;
    }
    public void setID(int id){
        this.id = id;
    }
}