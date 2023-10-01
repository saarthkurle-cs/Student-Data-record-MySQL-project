import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

class A1 extends JFrame implements ActionListener{
	Container c;
	JLabel lbl_msg;
	JButton btn_add;
	JButton btn_view;
	JButton btn_update;
	JButton btn_delete;
	JButton btn_charts;

	A1(){
		c = getContentPane();

		FlowLayout f1 = new FlowLayout();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		Font f = new Font("SansSerif", Font.PLAIN , 30);
		Font f2 = new Font("SansSerif", Font.BOLD , 30);

		lbl_msg = new JLabel("Student Management App", JLabel.CENTER);	lbl_msg.setFont(f2);	lbl_msg.setBounds(0, -20, 470, 100);
		lbl_msg.setForeground(Color.MAGENTA);
		c.add(BorderLayout.NORTH ,lbl_msg);

		btn_add = new JButton("Add");		btn_add.setBounds(20,60,200,50);	btn_add.setFont(f);		btn_add.setForeground(Color.BLUE);		c.add(btn_add);	
		btn_view = new JButton("View");		btn_view.setBounds(250,60,200,50);	btn_view.setFont(f);	btn_view.setForeground(Color.BLUE);		c.add(btn_view);
		btn_update = new JButton("Update");	btn_update.setBounds(20,125,200,50);	btn_update.setFont(f);	btn_update.setForeground(Color.BLUE);	c.add(btn_update);
		btn_delete = new JButton("Delete");	btn_delete.setBounds(250,125,200,50);	btn_delete.setFont(f);	btn_delete.setForeground(Color.BLUE);	c.add(btn_delete);
		btn_charts = new JButton("Charts");	btn_charts.setBounds(135,195,200,50);	btn_charts.setFont(f);	btn_charts.setForeground(Color.BLUE);	c.add(btn_charts);

		btn_add.addActionListener(this);
		btn_view.addActionListener(this);
		btn_charts.addActionListener(this);
		btn_update.addActionListener(this);
		btn_delete.addActionListener(this);

		setSize(470, 330);
		setTitle("Student Management App");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource().equals(btn_add)){
			Add add = new Add();
			add.setVisible(true);
		}
		if(ae.getSource().equals(btn_view)){
			View view = new View();
			view.setVisible(true);
		}
		if(ae.getSource().equals(btn_charts)){
			Student_charts chart = new Student_charts();
			chart.setVisible(true);
		}
		if(ae.getSource().equals(btn_delete)){
			Delete del = new Delete();
			del.setVisible(true);
		}
		if(ae.getSource().equals(btn_update)){
			Update up = new Update();
			up.setVisible(true);
		}
	}
}
class checkNum{
	public static boolean isNum(String str){
		int len = str.length();
		for(int i=0;i<len;i++){
			if(!Character.isLetter(str.charAt(i))){
				return true;
			}
		}
		return false;
	}
}

class checkRecord{
	public static boolean checkRec(int rno){
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){
			String check = "select rno from app2 where rno=?";
			PreparedStatement ps = con.prepareStatement(check);
			ps.setInt(1, rno);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			} 
		} catch (SQLException e){
			System.out.println( "issue " + e);
		} catch (NumberFormatException n){
			System.out.println( "Please enter numbers");
		}
		return false;
	}
}

class Add extends JFrame implements ActionListener{
	Container c;
	JLabel lbl_rno; JLabel lbl_name; JLabel lbl_marks1; JLabel lbl_marks2; JLabel lbl_marks3;
	JButton btn_save; JButton btn_back;
	JTextField txt_rno; JTextField txt_name; JTextField txt_marks1; JTextField txt_marks2; JTextField txt_marks3; 
	checkNum cn;
	checkRecord cr;
	
	Add(){
		c = getContentPane();
		FlowLayout f1 = new FlowLayout();
		c.setLayout(f1);
		c.setBackground(Color.WHITE);
		Font f = new Font("SansSerif", Font.PLAIN , 16);

		lbl_rno = new JLabel("Enter Roll Number:", JLabel.CENTER);	  lbl_rno.setFont(f);		lbl_rno.setForeground(Color.MAGENTA);	c.add(lbl_rno);
		txt_rno = new JTextField(15); 								  txt_rno.setFont(f); 		c.add(txt_rno);

		lbl_name = new JLabel("Enter Name:", JLabel.CENTER); 	  	  lbl_name.setFont(f);		lbl_name.setForeground(Color.MAGENTA);	c.add(lbl_name);
		txt_name = new JTextField(15); 								  txt_name.setFont(f); 		c.add(txt_name);

		lbl_marks1 = new JLabel("Enter Sub Marks 1:", JLabel.CENTER); lbl_marks1.setFont(f);	lbl_marks1.setForeground(Color.MAGENTA);	c.add(lbl_marks1);
		txt_marks1 = new JTextField(15); 							  txt_marks1.setFont(f); 	c.add(txt_marks1);

		lbl_marks2 = new JLabel("Enter Sub Marks 2:", JLabel.CENTER); lbl_marks2.setFont(f);	lbl_marks2.setForeground(Color.MAGENTA);	c.add(lbl_marks2);
		txt_marks2 = new JTextField(15); 							  txt_marks2.setFont(f); 	c.add(txt_marks2);

		lbl_marks3 = new JLabel("Enter Sub Marks 3:", JLabel.CENTER); lbl_marks3.setFont(f);	lbl_marks3.setForeground(Color.MAGENTA);	c.add(lbl_marks3);
		txt_marks3 = new JTextField(15); 							  txt_marks3.setFont(f); 	c.add(txt_marks3);

		btn_save = new JButton("Save");		btn_save.setFont(f);		c.add(btn_save);
		btn_back = new JButton("Back");		btn_back.setFont(f);		c.add(btn_back);

		btn_save.addActionListener(this);
		btn_back.addActionListener(this);
    
		setSize(250, 400);
		setTitle("Add Student");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		cn = new checkNum();
		cr = new checkRecord();
			if(ae.getSource().equals(btn_save)){
				try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){
					
					String str_rno = txt_rno.getText();
					String name = txt_name.getText();
					String marks1_str = txt_marks1.getText();
					String marks2_str = txt_marks2.getText();
					String marks3_str = txt_marks3.getText();

					if(Integer.parseInt(marks1_str)>=0 && Integer.parseInt(marks2_str)>=0 && Integer.parseInt(marks3_str)>=0 && Integer.parseInt(marks1_str)<=100 && Integer.parseInt(marks2_str)<=100 && Integer.parseInt(marks3_str)<=100){
						if(name.length()>=2 && !cn.isNum(name)){

							if(cr.checkRec(Integer.parseInt(str_rno))){
								JOptionPane.showMessageDialog(c, "Record Already Exists");
							} else{
								String sql = "insert into app2 values(?, ?, ?, ?, ?)";
								PreparedStatement pst = con.prepareStatement(sql);
								pst.setInt(1, Integer.parseInt(str_rno)); 
								pst.setString(2, name); 
								pst.setInt(3, Integer.parseInt(marks1_str)); 
								pst.setInt(4, Integer.parseInt(marks2_str));
								pst.setInt(5, Integer.parseInt(marks3_str));
								pst.executeUpdate();
								JOptionPane.showMessageDialog(c, "Thanks");
							}
							
						} else{
							JOptionPane.showMessageDialog(c, "Please enter Name with length >=2 and only containing alphabets");
						}
					} else{
						JOptionPane.showMessageDialog(c, "Please enter marks between 0 to 100");
					}
					
					txt_rno.setText("");	txt_marks1.setText("");
					txt_name.setText("");	txt_marks2.setText("");	txt_marks3.setText("");
				} catch (SQLException e){
					JOptionPane.showMessageDialog(c, "issue " + e);
				} catch (NumberFormatException n){
					JOptionPane.showMessageDialog(c, "Please enter numbers");
				}
			}
			if(ae.getSource().equals(btn_back)){
				this.dispose();
			}
		}
}

class View extends JFrame{
	Container c;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);

	View(){
		c = getContentPane();
		FlowLayout f1 = new FlowLayout();
		c.setLayout(f1);
		Font f = new Font("SansSerif", Font.PLAIN , 16);

		model.addColumn("Rollno");
		model.addColumn("Name");
		model.addColumn("Mathematics");
		model.addColumn("Physics");
		model.addColumn("Economics");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){
			String sql = "select * from app2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)});
			}


		} catch(SQLException e){
			JOptionPane.showMessageDialog(c, "issue " + e);
		}

		JScrollPane js = new JScrollPane(table);
		c.add(js);

		setSize(470, 330);
		setTitle("View Data");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
}

class Student_charts extends JFrame{
	
	Student_charts(){
		DefaultCategoryDataset ds = new DefaultCategoryDataset();

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){
			String sql = "select * from app2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ds.addValue(rs.getInt(3), rs.getString(2), "Mathematics");
				ds.addValue(rs.getInt(4), rs.getString(2), "Physics");
				ds.addValue(rs.getInt(5), rs.getString(2), "Economics");
			}
			conn.close();
		}
		catch (SQLException e){
			System.out.println( "issue " + e);
			
		} catch (NumberFormatException e){
			System.out.println("Please enter numbers" );
		}

		JFreeChart ch = ChartFactory.createBarChart("Student's Performance", "Subject",
			"Marks", ds, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel cp = new ChartPanel(ch);
		setContentPane(cp);
		try{
			File f = new File("Student.jpeg");
			ChartUtilities.saveChartAsJPEG(f, ch, 500, 400);
		} catch(IOException e){
			System.out.println("issue " + e);
		}

		setTitle("Charts");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
}

class Delete extends JFrame implements ActionListener{
	Container c;
	JLabel lbl_msg;
	JTextField txt_rno;
	JButton btn_delete;
	JButton btn_back;
	checkNum cn;

	Delete(){
		c = getContentPane();
		FlowLayout f1 = new FlowLayout();
		c.setLayout(f1);
		c.setBackground(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 30);

		lbl_msg = new JLabel("Enter Roll no to delete record: ");
		lbl_msg.setFont(f);		lbl_msg.setForeground(Color.GRAY);
		c.add(lbl_msg);

		txt_rno = new JTextField(15);
		txt_rno.setFont(f);
		c.add(txt_rno);

		btn_delete = new JButton("Delete");
		btn_delete.setFont(f);
		c.add(btn_delete);

		btn_back = new JButton("Back");
		btn_back.setFont(f);
		c.add(btn_back);

		btn_delete.addActionListener(this);
		btn_back.addActionListener(this);

		setTitle("Delete Record");
		setSize(500, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		cn = new checkNum();
		String str_rno = txt_rno.getText();
		
		if(ae.getSource().equals(btn_delete)){
			if(cn.isNum(str_rno)){

				try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){

					String check = "select rno from app2 where rno=?";
					PreparedStatement ps = conn.prepareStatement(check);
					ps.setInt(1, Integer.parseInt(str_rno));
					ResultSet rs = ps.executeQuery();


					if(Integer.parseInt(str_rno)>=0 && Integer.parseInt(str_rno)<=100){
						if(rs.next()){
							String sql = "delete from app2 where rno = ?";
							PreparedStatement pst = conn.prepareStatement(sql);
							pst.setInt(1, Integer.parseInt(str_rno));
							pst.executeUpdate();
							JOptionPane.showMessageDialog(c, "Record has been deleted");
							txt_rno.setText("");
							
						} else {
							JOptionPane.showMessageDialog(c, "Record does not exist");		
						}
					} else {
						JOptionPane.showMessageDialog(c, "Please enter numbers between 0 and 100");
					}
				} catch (SQLException e) {
						JOptionPane.showMessageDialog(c, "issue " + e);
				} catch (NumberFormatException n){
						JOptionPane.showMessageDialog(c, "Please enter numbers");
				}
			} else{
				JOptionPane.showMessageDialog(c, "Please enter numbers");
			}
		}

		if(ae.getSource().equals(btn_back)){
			this.dispose();	
		}
	}
}

class Update extends JFrame implements ActionListener{
	Container c;
	JLabel lbl_rno; JLabel lbl_name; JLabel lbl_marks1; JLabel lbl_marks2; JLabel lbl_marks3;
	JButton btn_update; JButton btn_back;  JButton retrieve;
	JTextField txt_rno; JTextField txt_name; JTextField txt_marks1; JTextField txt_marks2; JTextField txt_marks3; 
	checkNum cn;
	checkRecord cr;

	Update(){
		c = getContentPane();
		FlowLayout f1 = new FlowLayout();
		c.setLayout(f1);
		c.setBackground(Color.WHITE);
		Font f = new Font("SansSerif", Font.PLAIN , 16);

		lbl_rno = new JLabel("Enter Roll Number:", JLabel.CENTER);	  lbl_rno.setFont(f);		lbl_rno.setForeground(Color.MAGENTA);	c.add(lbl_rno);
		txt_rno = new JTextField(15); 								  txt_rno.setFont(f); 		c.add(txt_rno);

		retrieve = new JButton("Retrieve Data");	retrieve.setFont(f);	c.add(retrieve);

		lbl_name = new JLabel("Enter Name:", JLabel.CENTER); 	  	  lbl_name.setFont(f);		lbl_name.setForeground(Color.MAGENTA);	c.add(lbl_name);
		txt_name = new JTextField(15); 								  txt_name.setFont(f); 		c.add(txt_name);

		lbl_marks1 = new JLabel("Enter Sub Marks 1:", JLabel.CENTER); lbl_marks1.setFont(f);	lbl_marks1.setForeground(Color.MAGENTA);	c.add(lbl_marks1);
		txt_marks1 = new JTextField(15); 							  txt_marks1.setFont(f); 	c.add(txt_marks1);

		lbl_marks2 = new JLabel("Enter Sub Marks 2:", JLabel.CENTER); lbl_marks2.setFont(f);	lbl_marks2.setForeground(Color.MAGENTA);	c.add(lbl_marks2);
		txt_marks2 = new JTextField(15); 							  txt_marks2.setFont(f); 	c.add(txt_marks2);

		lbl_marks3 = new JLabel("Enter Sub Marks 3:", JLabel.CENTER); lbl_marks3.setFont(f);	lbl_marks3.setForeground(Color.MAGENTA);	c.add(lbl_marks3);
		txt_marks3 = new JTextField(15); 							  txt_marks3.setFont(f); 	c.add(txt_marks3);

		btn_update = new JButton("Update");		btn_update.setFont(f);		c.add(btn_update);
		btn_back = new JButton("Back");		btn_back.setFont(f);		c.add(btn_back);

		retrieve.addActionListener(this);
		btn_update.addActionListener(this);
		btn_back.addActionListener(this);

		setTitle("Update Info");
		setSize(250, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		cn = new checkNum();
		cr = new checkRecord();
		//try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "java", "abc123")){
			if(ae.getSource().equals(retrieve)){
				try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){
					String str_rno = txt_rno.getText();
					if(cr.checkRec(Integer.parseInt(str_rno))){
						String sql = "select rno, name, marks1, marks2, marks3 from app2 where rno=" + str_rno;
						PreparedStatement pst = con.prepareStatement(sql);
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next()){
							txt_name.setText(rs.getString(2));
							txt_marks1.setText(String.valueOf(rs.getInt(3)));
							txt_marks2.setText(String.valueOf(rs.getInt(4)));
							txt_marks3.setText(String.valueOf(rs.getInt(5)));
						}
					} 
					else{
						JOptionPane.showMessageDialog(c, "Record does not exist for roll number " + str_rno);
					}
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(c, "issue " + e);
			} catch (NumberFormatException n){
				JOptionPane.showMessageDialog(c, "Please enter numbers");
			}
			}

			if(ae.getSource().equals(btn_update)){
				try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", "root", "password")){

					String str_rno = txt_rno.getText();
					String str_name = txt_name.getText();
					String marks1_str = txt_marks1.getText();
					String marks2_str = txt_marks2.getText();
					String marks3_str = txt_marks3.getText();

					if(Integer.parseInt(marks1_str)>=0 && Integer.parseInt(marks2_str)>=0 && Integer.parseInt(marks3_str)>=0 && Integer.parseInt(marks1_str)<=100 && Integer.parseInt(marks2_str)<=100 && Integer.parseInt(marks3_str)<=100){
						if(str_name.length()>=2 && !cn.isNum(str_name)){

							if(cr.checkRec(Integer.parseInt(str_rno))){
								String sql = "update app2 set name=?, marks1=?, marks2=?, marks3=? where rno=?";
								PreparedStatement pst = conn.prepareStatement(sql);
								pst.setString(1, str_name);
								pst.setInt(2, Integer.parseInt(marks1_str));
								pst.setInt(3, Integer.parseInt(marks2_str));
								pst.setInt(4, Integer.parseInt(marks3_str));
								pst.setInt(5, Integer.parseInt(str_rno));
								pst.executeUpdate();

								JOptionPane.showMessageDialog(c, "Record Updated");
								txt_rno.setText("");	txt_marks1.setText("");
								txt_name.setText("");	txt_marks2.setText("");	txt_marks3.setText("");
							} else{
								JOptionPane.showMessageDialog(c, "Record does not exist. Please go to the Add window to add one.");
							}
						} else{
							JOptionPane.showMessageDialog(c, "Please enter Name with length >=2 and only containing alphabets");
							}
						} else{
							JOptionPane.showMessageDialog(c, "Please enter marks between 0 to 100");
						}
					}
					catch (SQLException e) {
						JOptionPane.showMessageDialog(c, "issue " + e);
					} catch (NumberFormatException n){
						JOptionPane.showMessageDialog(c, "Please enter numbers");
					}
				}

			if(ae.getSource().equals(btn_back)){
				this.dispose();	
			}
		} 
	
}

class A1Int{
	public static void main(String[] args) {
		A1 a = new A1();
	}
}
