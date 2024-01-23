import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



class Rolls_Royce
{
	public static void main(String[] args)throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rolls_royce", "root", "");

		RollsRoyce r = new RollsRoyce();
		ProgressBarExample m = new ProgressBarExample();
		Scanner sc = new Scanner(System.in);

		LocalDateTime currentDateTime = LocalDateTime.now();

		DateTimeFormatter formate_date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formate_time = DateTimeFormatter.ofPattern("HH:mm:ss");

		String date = currentDateTime.format(formate_date);
        String time=currentDateTime.format(formate_time);
		
        if(con!=null)
        {
            System.out.println("CONNETION DONE");
        }
        else
        {
            System.out.println("SOME ERROR");
        }
		JOptionPane.showMessageDialog(null, "HELLO SIR/MAM WLCOME TO ROHIT'S ROLLS ROYCE CUSTOMIZATION PROGRAM");

		System.out.println("");

		int a = 0;
		String mrormrsname = JOptionPane.showInputDialog("WHAT IS YOUR NAME MR./MRS.?");
		while (true) 
		{
			if (mrormrsname.length() > 0) 
			{
				System.out.println("==========MR./MRS. " + mrormrsname + " WHAT WHOULD YOU LIKE!!==========");
				break;
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "PLASE ENTER YOUR NAME");
				mrormrsname = JOptionPane.showInputDialog("WHAT IS YOUR NAME MR./MRS.?");
				if (++a > 1) 
				{
					JOptionPane.showMessageDialog(null, "SOORY TRY LATTER");
					System.exit(0);
				}
			}
		}

		for (;;) 
		{

			System.out.println();
			System.out.println("1.VIEW ROLLS-ROYCE");
			System.out.println("2.BUY DIRECT ROLLS-ROYCE");
			System.out.println("3.CUSTOMIZE ROLLS-ROYCE");
			System.out.println("4.SERVICE ROLLS-ROYCE");
			System.out.println("5.EXIT");
			System.out.println();
			while(!sc.hasNextInt())
            {
                JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
                sc.nextLine();
            }

			int x = sc.nextInt();
			if(x==1)
			{
				String c_name="";
				System.out.println("ENTER TO VIEW CAR ");
				System.out.println("1.GHOST");
				System.out.println("2.WRAITH");
				System.out.println("3.CULLINAN");
				System.out.println("4.DAWN");
				System.out.println("5.PHANTOM");
				for(;;)
				{
					
					int view=sc.nextInt();
					switch(view)
					{
						case 1:
						{
							c_name="GHOST";
							BufferedReader br=new BufferedReader(new FileReader("CARS\\GHOST.txt"));
							String line =br.readLine();
							while(line!=null)
							{
								System.out.println(line);
								line=br.readLine();
							}
							br.close();
						}
						break;
						case 2:
						{
							c_name="WRAITH";
							BufferedReader br=new BufferedReader(new FileReader("CARS\\WRAITH.txt"));
							String line =br.readLine();
							while(line!=null)
							{
								System.out.println(line);
								line=br.readLine();
							}
							br.close();

						}
						break;
						case 3:
						{
							c_name="CULLINAN";
							BufferedReader br=new BufferedReader(new FileReader("CARS\\CULLINAN.txt"));
							String line =br.readLine();
							while(line!=null)
							{
								System.out.println(line);
								line=br.readLine();
							}
							br.close();

						}
						break;
						case 4:
						{
							c_name="DAWN";
							BufferedReader br=new BufferedReader(new FileReader("CARS\\DAWN.txt"));
							String line =br.readLine();
							while(line!=null)
							{
								System.out.println(line);
								line=br.readLine();
							}
							br.close();

						}
						break;
						case 5:
						{
							c_name="PHANTOM";
							BufferedReader br=new BufferedReader(new FileReader("CARS\\PHANTOM.txt"));
							String line =br.readLine();
							while(line!=null)
							{
								System.out.println(line);
								line=br.readLine();
							}
							br.close();

						}
						break;
						default:
						JOptionPane.showMessageDialog(null, "ENTER VALID CHOICE.", "ERROR",JOptionPane.ERROR_MESSAGE);

						

					}
					if(view>=1 && view<=5)
					{
						break;
					}
				

				}
				r.feedback();// FEEDBACK
				Thread.sleep(2000);

				String s1="insert into rr_view(Customer_Name,View_Car_Name) values(?,?)";
				PreparedStatement pst=con.prepareStatement(s1);
				pst.setString(1,mrormrsname);
                pst.setString(2,c_name);
				int r1=pst.executeUpdate();
                if(r1>0)
                {
                    JOptionPane.showMessageDialog(null,"DATA INSERTED IN DATA BASE");
                }
                else
                {
                    System.out.println("FAILED ERROR!!");
                }

				JOptionPane.showMessageDialog(null, "THANK YOU FOR WISITING MR./MRS. " + mrormrsname + "");
				System.out.println();
				
				
				
				

			}
			else if (x == 3) // FOR BUY DIRECT CAR
			{
				System.out.println("******MR./MRS. " + mrormrsname + " WELCOME TO THE ROLLS-ROYCE CUSTOMIZATION******");
				System.out.println();

				// FOR CHOOSE THE MODEL
				System.out.println("=>=>=>=>MR./MRS. " + mrormrsname + " WHICH MODEL WOULD YOU LIKE TO CUSTOMIZE?<=<=<=<=");
				System.out.println();
                List<Upgrade> models = new ArrayList<>();
				models.add(new Upgrade("GHOST    ", 315000));
                models.add(new Upgrade("WRAITH   ", 338000));
                models.add(new Upgrade("CULLINAN ", 340000));
                models.add(new Upgrade("DAWN     ", 356000));
                models.add(new Upgrade("PHANTOM  ", 450000));

                System.out.println("NO. TYPE             COST");
                for (int i = 0; i < models.size(); i++) 
                {
                    System.out.println((i + 1) + ".  " + models.get(i).getName() + "      $" + models.get(i).getPrice());
                }
                System.out.println();

                String modelName = "";
                double modelPrice = 0;

                while (true) 
				{
                    System.out.println("ENTER CHOICE");
                    while (!sc.hasNextInt()) 
					{
                        JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        sc.nextLine();
                    }
                    int modelChoice = sc.nextInt();
                    if (modelChoice >= 1 && modelChoice <= models.size()) 
					{
                        modelName = models.get(modelChoice - 1).getName();
                        modelPrice = models.get(modelChoice - 1).getPrice();
                        break;
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "INVALID CHOICE! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.out.println("CHOSEN MODEL:       " + modelName);
                System.out.println("CHOSEN MODEL PRICE: $" + modelPrice);

				System.out.println();

				// FOR CHOOSE THE EXTERIOR COLOR
				System.out.println("=>=>=>=>MR./MRS. " + mrormrsname + " WHICH EXTERIOR COLOR WOULD YOU LIKE?<=<=<=<=");
				System.out.println();
                
                List<Upgrade> color = new ArrayList<>();
                color.add(new Upgrade("DIAMOND BLACK     ", 10500));
                color.add(new Upgrade("JUBILEE SILVER    ", 10500));
                color.add(new Upgrade("ARCTIC WHITE      ", 10500));
                color.add(new Upgrade("DARK EMERALD      ", 10500));
                color.add(new Upgrade("SMOKY QUARTZ      ", 10500));

                System.out.println("NO. TYPE                     COST");
                for (int i = 0; i < models.size(); i++) 
				{
                    System.out.println((i + 1) + ".  " + color.get(i).getName() + "      $" + color.get(i).getPrice());
                }
                System.out.println();

                String colorname = "";
                double colorprice = 0;

                while (true) 
				{
                    System.out.println("ENTER CHOICE");
                    while (!sc.hasNextInt()) 
					{
                        JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        sc.nextLine();
                    }
                    int colorchoice = sc.nextInt();
                    if (colorchoice >= 1 && colorchoice <= color.size()) 
					{
                        colorname = color.get(colorchoice - 1).getName();
                        colorprice = color.get(colorchoice - 1).getPrice();
                        break;
                    } 
					else 
					{
                        JOptionPane.showMessageDialog(null, "INVALID CHOICE! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                System.out.println("CHOSEN COLOR:       " + colorname);
                System.out.println("CHOSEN COLOR PRICE: $" + colorprice);

				System.out.println();

				// FOR CHOOSE THE INTERIOR
				System.out.println("=>=>=>=>MR./MRS. " + mrormrsname + " WHICH INTERIOR  WOULD YOU LIKE?<=<=<=<=");
				System.out.println();
				List<Upgrade> interior = new ArrayList<>();
                interior.add(new Upgrade("LEATHER BLACK      ", 15000));
                interior.add(new Upgrade("WOOD TRIM          ", 18000));
                interior.add(new Upgrade("LAMBSWOOL CARPETS  ", 20000));
               

                System.out.println("NO.      TYPE                  COST");
                for (int i = 0; i < interior.size(); i++) 
				{
                    System.out.println((i + 1) + ".  " + interior.get(i).getName() + "      $" + interior.get(i).getPrice());
                }
                System.out.println();

                String interiorName = "";
                double interiorPrice = 0;

                while (true) 
				{
                    System.out.println("ENTER CHOICE");
                    while (!sc.hasNextInt()) 
					{
                        JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        sc.nextLine();
                    }
                    int interiorchoice = sc.nextInt();
                    if (interiorchoice >= 1 && interiorchoice <= interior.size()) 
					{
                        interiorName = interior.get(interiorchoice - 1).getName();
                        interiorPrice = interior.get(interiorchoice - 1).getPrice();
                        break;
                    }
					else 
					{
                        JOptionPane.showMessageDialog(null, "INVALID CHOICE! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                System.out.println("CHOSEN INTERIOR:       " + interiorName);
                System.out.println("CHOSEN INTERIOR PRICE: $" + interiorPrice);

				System.out.println();

				// FOR CHOOSE ADDITIONAL OPTIONS
				System.out.println("=>=>=>=>MR./MRS. " + mrormrsname + " WHICH ADDITIONAL OPTIONS WOULD YOU LIKE?<=<=<=<=");
				System.out.println();
				List<Upgrade> additional = new ArrayList<>();
                additional.add(new Upgrade("PANORAMIC SUNROOF         ", 7500));
                additional.add(new Upgrade("REAR SEAT ENTERTAINMENT   ", 8500));
                additional.add(new Upgrade("NIGHTVISION              ", 24000));
                additional.add(new Upgrade("ADAPTIVE CONTROL          ", 10200));
                

                System.out.println("NO.     TYPE                   COST");
                for (int i = 0; i < additional.size(); i++) 
				{
                    System.out.println((i + 1) + ".  " + additional.get(i).getName() + "      $" + additional.get(i).getPrice());
                }
                System.out.println();

                String additionalname = "";
                double additionalprice = 0;

                while (true) 
				{
                    System.out.println("ENTER CHOICE");
                    while (!sc.hasNextInt()) 
					{
                        JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        sc.nextLine();
                    }
                    int additionalchoice = sc.nextInt();
                    if (additionalchoice >= 1 && additionalchoice <= additional.size()) 
					{
                        additionalname = additional.get(additionalchoice - 1).getName();
                        additionalprice = additional.get(additionalchoice - 1).getPrice();
                        break;
                    }
					else 
					{
                        JOptionPane.showMessageDialog(null, "INVALID CHOICE! TRY AGAIN.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                System.out.println("CHOSEN ADDITIONAL:       " + additionalname);
                System.out.println("CHOSEN ADDITIONAL PRICE: $" + additionalprice);

				System.out.println();
				System.out.println("==================================================");

				// DISPLAY CUSTOMIZATION
				System.out.println("YOU CHOOSE LUXURYS " + modelName + "CAR");
				System.out.println();
				System.out.println("CAR MODEL:                     " + modelName);
				System.out.println("CAR  EXTERIOR COLOR :          " + colorname);
				System.out.println("CAR INTERIOR :                 " + interiorName);
				System.out.println("CAR ADDITIONAL ATTECHMENTS:    " + additionalname);
				System.out.println();

				double totalPrice = modelPrice + colorprice + interiorPrice + additionalprice;

				System.out.println("=>PRICE OF CAR IS: " + "$" + totalPrice);
				System.out.println();
				System.out.println("=>INCLUDE 6% GST AND ONTHER PAPER WORK.");
				double T = ((6 * totalPrice) / 100);
				System.out.println("=>THE TOTAL PRICE OF YOUR ROLLS-ROYCE " + modelName + " IS: $" + (T + totalPrice));
				System.out.println();

				r.payment();// PAYMENT
				m.setVisible(true);
        		m.iterate();

				
				BufferedWriter bw = new BufferedWriter(new FileWriter("Bill.txt"));
            	bw.write("");
				// PRINT BILL
				bw.write("=================================================================================\n");
				bw.write("||                   ROHIT'S ROLLS ROYCE CUSTOMIZATION                         ||\n");
				bw.write("||-----------------------------------------------------------------------------||\n");
				bw.write("||                              <-BILL->                                       ||\n");
				bw.write("|| Date :"+date+"                                                            ||\n");
				bw.write("|| Time :"+time+"                                                              ||\n");
				bw.write(String.format("|| NAME :%-25s                                             ||%n", mrormrsname));
				bw.write("||-----------------------------------------------------------------------------||\n");
				bw.write("||                                                               COST          ||\n");
				bw.write(String.format("|| CAR MODEL :                     %-25s " + "$" + "%-1f    ||%n", modelName,modelPrice));
				bw.write(String.format("|| CAR COLOR :                     %-25s " + "$" + "%-2f     ||%n", colorname,colorprice));
				bw.write(String.format("|| CAR INTERIOR :                  %-25s " + "$" + "%-2f     ||%n", interiorName,interiorPrice));
				bw.write(String.format("|| CAR ADDITIONAL ATTECHMENTS :    %-25s " + "$" +"%-2f     ||%n", additionalname,additionalprice));
				bw.write("---------------------------------------------------------------------------------\n");
				bw.write(String.format("|| PRICE:                                                    " + "$" + "%-10f    ||%n",totalPrice));
				bw.write(String.format("|| SIX PERCENTAGE GST:                                       " + "$" + "%-10f     ||%n",T));
				bw.write("---------------------------------------------------------------------------------\n");
				bw.write(String.format("||TOTAL PRICE IS=>                                       " + "$" + "%-10f        ||%n",(T + totalPrice)));
				bw.write("=================================================================================\n");
				bw.flush();
				bw.close();

				BufferedReader br=new BufferedReader(new FileReader("Bill.txt"));
				String line =br.readLine();
				while(line!=null)
				{
					System.out.println(line);
					line=br.readLine();
				}
				br.close();
				Thread.sleep(3000);
				r.feedback();// FEEDBACK
				Thread.sleep(2000);

				String s1="insert into rr_customization(Customer_Name,Customize_Car_Name,Car_Price,Phone_Number) values(?,?,?,?)";
				PreparedStatement pst=con.prepareStatement(s1);
				pst.setString(1,mrormrsname);
                pst.setString(2,modelName);
                pst.setDouble(3,(T+totalPrice));
				pst.setLong(4,r.mobileNumber);
				int r1=pst.executeUpdate();
                if(r1>0)
                {
                    JOptionPane.showMessageDialog(null,"DATA INSERTED IN DATA BASE");
                }
                else
                {
                    System.out.println("FAILED ERROR!!");
                }

				JOptionPane.showMessageDialog(null, "THANK YOU FOR WISITING MR./MRS. " + mrormrsname + "");
				System.out.println();
				System.out.println();
			}

			else if (x == 2) // FOR DIRECT CAR BUYING
			{
				String name = "";
				String type = "";
				String bodytype = "";
				int numberofdor = 0;
				String fuel_type = "";
				double fuel_tank = 0;
				short engine = 0;
				double milage = 0.0;
				int seatingcapacity = 0;
				String wheel_type = "";
				double top_speed = 0;
				double price=0;

				System.out.println("=>=>=>=>MR./MRS. " + mrormrsname + " WHICH CAR WHOULD YOU LIKE TO BUY<=<=<=<=");
				System.out.println();

				System.out.println("1.  GHOST ");
				System.out.println("2.  WRAITH ");
				System.out.println("3.  CULLINAN ");
				System.out.println("4.  DAWN ");
				System.out.println("5.  PHANTOM ");

				for (;;) 
				{
					System.out.println("ENTER CHOICE");
					while(!sc.hasNextInt())
					{
						JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
						sc.nextLine();
					}

					int redy = sc.nextInt();
					switch (redy) 
					{
						case 1: 
						{
							System.out.println("==================================================");
							System.out.println("DETAILS OF ROLLS-ROYCE GHOST");
							name = "GHOST";
							type = "LUXURY";
							bodytype = "SEDEN";
							numberofdor = 4;
							fuel_type = "PETROL";
							fuel_tank = 83;
							engine = 6750;
							milage = 6.33;
							seatingcapacity = 5;
							wheel_type = "Alloy Wheels";
							top_speed = 250;
							price=350000;
						}
						break;
						case 2: 
						{
							System.out.println("==================================================");
							System.out.println("DETAILS OF ROLLS-ROYCE WRAITH");
							name = "WRAITH";
							type = "LUXURY";
							bodytype = "COUP";
							numberofdor = 4;
							fuel_type = "PETROL";
							fuel_tank = 83;
							engine = 6592;
							milage = 10.2;
							seatingcapacity = 4;
							wheel_type = "Alloy Wheels";
							top_speed = 250;
							price=400000;
						}
						break;
						case 3: 
						{
							System.out.println("==================================================");
							System.out.println("DETAILS OF ROLLS-ROYCE CULLINAN");
							name = "CULLINAN";
							type = "SPORT";
							bodytype = "SUV";
							numberofdor = 2;
							fuel_type = "PETROL";
							fuel_tank = 100;
							engine = 6750;
							milage = 9.5;
							seatingcapacity = 2;
							wheel_type = "Alloy Wheels";
							top_speed = 350;
							price=650000;
						}
						break;
						case 4: 
						{
							System.out.println("==================================================");
							System.out.println("DETAILS OF ROLLS-ROYCE DAWN");
							name = "DAWN";
							type = "LUXURY";
							bodytype = "CONVERTIBLE";
							numberofdor = 2;
							fuel_type = "PETROL";
							fuel_tank = 82;
							engine = 6598;
							milage = 9.2;
							seatingcapacity = 2;
							wheel_type = "Alloy Wheels";
							top_speed = 300;
							price=350000;
						}
						break;
						case 5: 
						{
							System.out.println("==================================================");
							System.out.println("DETAILS OF ROLLS-ROYCE PHANTOM");
							name = "PHANTOM";
							type = "LUXURY";
							bodytype = "SEDAN";
							numberofdor = 4;
							fuel_type = "PETROL";
							fuel_tank = 100;
							engine = 6749;
							milage = 9.8;
							seatingcapacity = 5;
							wheel_type = "Alloy Wheels";
							top_speed = 250;
							price=450000;
						}
						break;

						default:
						JOptionPane.showMessageDialog(null, "ENTER VALID CHOICE.", "ERROR",JOptionPane.ERROR_MESSAGE);

					}
					if (redy >= 1 && redy <= 5) 
					{
						break;
					}
				}

				System.out.println();
				System.out.println("NAME OF CAR:        " + name);
				System.out.println("TYPE IS:            " + type);
				System.out.println("BODYTYPE IS:        " + bodytype);
				System.out.println("NUMBER OF DOOR IS:  " + numberofdor);
				System.out.println("FULE TYPE:          " + fuel_type);
				System.out.println("FULE CAPACITY: 	    " + fuel_tank + " L");
				System.out.println("ENGINE:             " + engine + "CC");
				System.out.println("MILAGE:             " + milage + " KMPL");
				System.out.println("SEATING CAPACITY:   " + seatingcapacity);
				System.out.println("WHEEL TYPE:  	    " + wheel_type);
				System.out.println("TOP SPEED:  	    " + top_speed + " KM/H");
				System.out.println("PRICE IS:         " + price);
	
				System.out.println();

				System.out.println("==============================================================================");

				r.payment();// PAYMENT
				m.setVisible(true);
        		m.iterate();

				// PRINT BILL
				BufferedWriter bw = new BufferedWriter(new FileWriter("Bill.txt"));
            	bw.write("");
				bw.write("==============================================================================\n");
				bw.write("||                   ROHIT'S ROLLS ROYCE CUSTOMIZATION                      ||\n");
				bw.write("||--------------------------------------------------------------------------||\n");
				bw.write("||                              <-BILL->                                    ||\n");
				bw.write("||                           BUY DIRECT CAR                                 ||\n");
				bw.write("|| Date :"+date+"                                                         ||\n");
				bw.write("|| Time :"+time+"                                                           ||\n");
				bw.write(String.format("|| NAME :%-25s                                          ||%n", mrormrsname));
				bw.write("||--------------------------------------------------------------------------||\n");
				bw.write(String.format("|| NAME OF CAR:                                  %-25s  ||%n", name));
				bw.write(String.format("|| CAR TYPE IS:                                  %-25s  ||%n", type));
				bw.write(String.format("|| CAR BODY TYPE IS:	                         %-25s  ||%n", bodytype));
				bw.write(String.format("|| NUMBER OF DOOR:                                %-25s ||%n", numberofdor));
				bw.write(String.format("|| CAR FUEL TYP:                                 %-25s  ||%n", fuel_type));
				bw.write(String.format("|| CAR FUEL CAPACITY(L):                         %-25s  ||%n", fuel_tank));
				bw.write(String.format("|| CAR ENGINE(CC):                               %-25s  ||%n", engine));
				bw.write(String.format("|| CAR MILAGE(KM/PL):                            %-25s  ||%n", milage));
				bw.write(String.format("|| CAR SEATING CAPACITY:                         %-25s  ||%n", seatingcapacity));
				bw.write(String.format("|| CAR WHEEL TYPE:                               %-25s  ||%n", wheel_type));
				bw.write(String.format("|| CAR TOP SPEED(KM/H):                          %-25s  ||%n", top_speed));
				bw.write("------------------------------------------------------------------------------\n");
				bw.write(String.format("|| TOTAL PRICE IS    :                         "+"$"+" %-25s  ||%n", price));
				bw.write("==============================================================================\n");
				bw.flush();
				bw.close();

				BufferedReader br=new BufferedReader(new FileReader("Bill.txt"));
				String line =br.readLine();
				while(line!=null)
				{
					System.out.println(line);
					line=br.readLine();
				}
				br.close();
				Thread.sleep(3000);
				r.feedback();// FEEDBACK

				String s1="insert into rr_direct_buy(Customer_Name,Buy_Car_Name,Car_Price,Phone_Number) values(?,?,?,?)";
				PreparedStatement pst=con.prepareStatement(s1);
				pst.setString(1, mrormrsname);
                pst.setString(2, name);
                pst.setDouble(3, price);
				pst.setLong(4, r.mobileNumber);
				int r1=pst.executeUpdate();
                if(r1>0)
                {
                    JOptionPane.showMessageDialog(null,"DATA INSERTED IN DATA BASE");
                }
                else
                {
                    System.out.println("FAILED ERROR!!");
                }

				JOptionPane.showMessageDialog(null, "THANK YOU FOR WISITING MR./MRS. " + mrormrsname + "");
				System.out.println();
				System.out.println();
			} 
			else if (x == 4)// FOR SERVICE CAR
			{
				System.out.println("=====>MR./MRS. "+mrormrsname + " WHICH SERVICES DO YOU WANT? (ENTER MULTIPLE SEPARATED BY SPACES)");
				r.service();// SERVICE
				System.out.println();
				r.feedback();// FEEDBACK
				String s1="insert into rr_service(Customer_Name,Service_Cost,Phone_Number) values(?,?,?)";
				PreparedStatement pst=con.prepareStatement(s1);
				pst.setString(1, mrormrsname);
                pst.setInt(2, r.totalCost);
                pst.setDouble(3, r.mobileNumber);
				int r1=pst.executeUpdate();
                if(r1>0)
                {
                    JOptionPane.showMessageDialog(null,"DATA INSERTED IN DATA BASE");
                }
                else
                {
                    System.out.println("FAILED ERROR!!");
                }
				System.out.println();
				System.out.println();

				JOptionPane.showMessageDialog(null, "THANK YOU FOR WISITING MR./MRS. " + mrormrsname + "");
			} 
			else if (x == 5)// FOR EXIT
			{
				JOptionPane.showMessageDialog(null, "EXIT...");
				System.exit(0);
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "ENTER VALID CHOICE.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

class RollsRoyce 
{
	Scanner sc = new Scanner(System.in);
	long mobileNumber, otp;
	int totalCost = 0;
	
	public void payment() // PAYMENT METHOD
	{

		System.out.println("WHICH METHOD DO YOU LIKE TO PAY:");
		System.out.println("1.CASH");
		System.out.println("2.NET BANKING");
		for (;;) 
		{
			System.out.println("ENTER CHOICE");
			while(!sc.hasNextInt())
            {
                JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
                sc.nextLine();
            }
			int p = sc.nextInt();
			sc.nextLine();
			switch (p) 
			{
				case 1: 
				{
					Random ran = new Random();
					

					while (true) 
					{
						System.out.print("ENTER YOUR 10 DIGIT MOBILE NUMBER: ");
						while(!sc.hasNextLong())
						{
							JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
							sc.nextLine();
						}
						mobileNumber = sc.nextLong();

						if (String.valueOf(mobileNumber).length() != 10) 
						{
							JOptionPane.showMessageDialog(null, "INVALID MOBILE NUMBER, PLEASE TRY AGAIN.");

						} 
						else 
						{
							break;
						}
					}
					otp = ran.nextInt(10000);
					JOptionPane.showMessageDialog(null, "OTP IS: " + String.format("%04d", otp));
					System.out.println("*OTP IS*: " + String.format("%04d", otp));

					while (true) 
					{
						System.out.print("ENTER OTP: ");
						while(!sc.hasNextInt())
						{
							JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
							sc.nextLine();
						}
						int userOtp = sc.nextInt();
						sc.nextLine();

						if (userOtp == otp) 
						{
							JOptionPane.showMessageDialog(null,"PAYMENT HAS DONE SUCESSFULLY, BIIL HAS BEEN PRINT AND SEND ON YOUR MOBILE");
							break;
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "INNCORECT OTP TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				break;
				case 2: 
				{
					long Acountnumber;
					int pin = 1234;

					while (true)
					{
						JOptionPane.showMessageDialog(null, "ACCOUNT NUMBER MUST BE OF 14 DIGIT");
						System.out.print("ENTER YOUR ACOUNT NUMBER: ");
						
						while(!sc.hasNextLong())
						{
							JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
							sc.nextLine();
						}

						Acountnumber = sc.nextLong();

						sc.nextLine();
						if (String.valueOf(Acountnumber).length() != 14) 
						{
							JOptionPane.showMessageDialog(null, "INVALID ACOUNT NUMBER, PLEASE TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);

						} 
						else 
						{

							break;
						}
					}
					while (true) 
					{
						System.out.print("ENTER PIN: ");
						while(!sc.hasNextInt())
						{
							JOptionPane.showMessageDialog(null, "INVALID INPUT! TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);
							sc.nextLine();
						}
						int userpin = sc.nextInt();

						sc.nextLine();
						if (userpin == pin) 
						{
							JOptionPane.showMessageDialog(null, "PAYMENT HAS DONE SUCESSFULLY, BIIL HAS BEEN PRINT ");
							break;
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "INCORRECT PIN. PLEASE TRY AGAIN.", "ERROR",JOptionPane.ERROR_MESSAGE);

						}
					}
				}
				break;
				default: 
				{
					JOptionPane.showMessageDialog(null, "INVALID CHOICE. PLEASE TRY AGAIN", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
			if (p >= 1 && p <= 2) 
			{
				break;
			}
		}

	}

	public void feedback()// FEEDBACK METHOD
	{
		String[] options = { "⭐", "⭐⭐", "⭐⭐⭐", "⭐⭐⭐⭐", "⭐⭐⭐⭐⭐" };
		int choice = JOptionPane.showOptionDialog(null, "GIVE RATINGS OUT OF 5", "RATE US", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		switch (choice) 
		{
			case 0:
			System.out.println();
			System.out.println("THANK YOU FOR YOUR FEEDBACK ! (POOR!!)");
			break;

			case 1:
			System.out.println();
			System.out.println("THANK YOU FOR YOUR FEEDBACK ! (AVARAGE!)");
			break;

			case 2:
			System.out.println();
			System.out.println("THANK YOU FOR YOUR FEEDBACK ! (GOOD)");
			break;

			case 3:
			System.out.println();
			System.out.println("THANK YOU FOR YOUR FEEDBACK ! (VERY GOOD!)");
			break;

			case 4:
			System.out.println();
			System.out.println("THANK YOU FOR YOUR FEEDBACK ! (EXCELLENT!!!)");
			break;

			default:
			System.out.println("INVALID CHOICE");
			break;
		}
	}

	public void service()throws Exception// SERVICE METHOD
	{
		Scanner sc = new Scanner(System.in);

		Map<String, Integer> services = new HashMap<>();
		services.put("OIL CHANGE", 300);
		services.put("BRAKE INSPECTION", 350);
		services.put("TIRE ROTATION", 100);
		services.put("PPF", 800);
		services.put("ENGINE CHECKING", 1000);
		services.put("ALL OVER SERVICE", 2000);

		System.out.println();

		int index = 1;
		for (Map.Entry<String, Integer> entry : services.entrySet()) 
		{
			System.out.printf("%-2d  %-20s %s %-4d %n", index, entry.getKey(), "$", entry.getValue());
			index++;
		}

		String serviceNumbers = sc.nextLine();
		String[] selectedServiceNumbers = serviceNumbers.split(" ");

		

		
		System.out.println();
		System.out.println("FIRST YOU HAVE TO PAY");
		payment();
		
		
		
	
		BufferedWriter bw = new BufferedWriter(new FileWriter("Bill.txt"));
        bw.write("");

		System.out.println();
		bw.write("=============================================\n");
		bw.write("||    ROHIT'S ROLLS ROYCE CUSTOMIZATION    ||\n");
		bw.write("||-----------------------------------------||\n");
		bw.write("||               <-BILL->                  ||\n");
		bw.write("||             SERVICE BILL                ||\n");
		bw.write("||-----------------------------------------||\n");

		for (String serviceNumber : selectedServiceNumbers) 
		{
			int selectedServiceIndex = Integer.parseInt(serviceNumber) - 1;
			String serviceName = (String) services.keySet().toArray()[selectedServiceIndex];
			int serviceCost = services.get(serviceName);
			totalCost += serviceCost;

			bw.write(String.format("|| %-20s         $%-4d      ||%n", serviceName, serviceCost));
		}

		bw.write("-------------------------------------------||\n");
		bw.write(String.format("|| TOTAL COST:                  $" + "%-4d      ||%n", totalCost));
		bw.write("=============================================\n");
		bw.flush();
		bw.close();

		BufferedReader br=new BufferedReader(new FileReader("Bill.txt"));
		String line =br.readLine();
		while(line!=null)
		{
			System.out.println(line);
			line=br.readLine();
		}
		br.close();
		Thread.sleep(3000);
		feedback();

		
	}
	
		
	

}
class ProgressBarExample extends JFrame 
{
    JProgressBar jb;
    int i = 0, num = 0;

    ProgressBarExample() 
	{
        setTitle("PRINTING THE BILL...");  // Set the title of the JFrame
        jb = new JProgressBar(0, 500);
        jb.setBounds(40, 40, 160, 30);
        jb.setValue(0);
        jb.setStringPainted(true);
        add(jb);
        setSize(250, 150);
        setLayout(null);
		setLocationRelativeTo(null);
    }

    public void iterate() 
    {
        while (i <= 500) 
        {
            jb.setValue(i);
            i = i + 20;
            try 
			{
            	Thread.sleep(250);
            } 
			catch (Exception e) 
			{
				
            }
			
        }
		i=0;
		setVisible(false);
    }
	
}
class Upgrade
{
	
    String name;
    double price;

    public Upgrade(String name, double price) 
	{
        this.name = name; 
        this.price = price;
    }

    public String getName() 
	{
        return name;
    }

    public double getPrice() 
	{
        return price;
    }
}