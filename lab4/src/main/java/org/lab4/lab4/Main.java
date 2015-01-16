

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Main extends JFrame implements ActionListener {
	static String logged_in_user = "";

	public Main() {
		final DB db = new DB();
		final BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

		// Панель меню
		JMenuBar menubar = new JMenuBar();

		// Меню
		JMenu menu = new JMenu("База данных");

		JMenuItem itm = new JMenuItem("Имена пользователей и их сообщения");
		menu.add(itm);
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					db.printUsersMessages();
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});

		itm = new JMenuItem("Количество пользователей");
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Количество пользователей: "
							+ db.UsersQuantity());
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});
		menu.add(itm);

		itm = new JMenuItem("ТОП-10 недавно входивших в систему пользователей");
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(db.topTen("Desc"));
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});
		menu.add(itm);

		itm = new JMenuItem("ТОП-10 давно не входивших в систему пользователей");
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(db.topTen("Asc"));
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});
		menu.add(itm);

		// Разделитель
		menu.add(new JSeparator());

		itm = new JMenuItem("Войти в систему");
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Логин:");
					String login = bufr.readLine();
					System.out.println("Пароль:");
					String pass = bufr.readLine();
					if (db.login(login, pass) != "")
						System.out.println(db.login(login, pass));
					else
						System.out
								.println("Неправильно введены логин или пароль");
				} catch (SQLException exc) {
					exc.printStackTrace();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		});
		menu.add(itm);

		itm = new JMenuItem("Ввести сообщение");
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (logged_in_user != "") {
						System.out.println("Введите сообщение:");

						String message = bufr.readLine();
						System.out.println(logged_in_user + "\t" + message);
						db.newMessage(logged_in_user, message);
					}
				} catch (SQLException exc) {
					exc.printStackTrace();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		});
		menu.add(itm);

		itm = new JMenuItem("Выйти из системы");
		itm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logged_in_user != "") {
					System.out.println("Пользователь " + logged_in_user
							+ " вышел из системы");
					logged_in_user = "";
				}
			}
		});
		menu.add(itm);

		// Добавление меню в панель меню
		menubar.add(menu);

		// Добавление панели меню в окно
		setJMenuBar(menubar);

		// Настройка окна
		setTitle("LR4_JDBC_PostgreSQL"); // Заголовок окна
		// Желательные размеры окна
		setPreferredSize(new Dimension(640, 480));
		// Завершить приложение при закрытии окна
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setVisible(true); 
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public static void main(String[] args) {
		new Main();
	}

}