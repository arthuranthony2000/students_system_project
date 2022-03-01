package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.Student;

public class Main {
	static final Scanner Console = new Scanner(System.in);
	static StudentDao studentDao = DaoFactory.createStudentDao();

	public static void main(String args[]) {

		while (screen() != 0);
	}

	public static Integer screen() {
		Integer option;
		System.out.println("####################################\n" + "#####  1 - INSERIR ALUNO   #########\n"
				+ "#####  0 - ENCERRAR PROGRAMA #######\n" + "####################################\n");
		option = Integer.parseInt(Console.nextLine());
		if (option == 0)
			return option;
		else {
			Student newStudent = insert();
			studentDao.insert(newStudent);
			System.out.println("Inserted! New id = " + newStudent.getNumber());
		}
		return option;
	}

	public static Student insert() {
		Integer number;
		System.out.println("####################################\n" + "#####            INSIRA O ID        #########\n"
				+ "####################################\n");
		number = Integer.parseInt(Console.nextLine());
		String name;
		System.out.println("####################################\n" + "#####            INSIRA O NOME      #########\n"
				+ "####################################\n");
		name = Console.nextLine();
		String course;
		System.out.println("####################################\n" + "#####            INSIRA O CURSO      ########\n"
				+ "####################################\n");
		course = Console.nextLine();
		Float nota1;
		System.out.println("####################################\n" + "#####            INSIRA A NOTA 1     ########\n"
				+ "####################################\n");
		nota1 = Float.parseFloat(Console.nextLine());
		Float nota2;
		System.out.println("####################################\n" + "#####            INSIRA A NOTA 2     ########\n"
				+ "####################################\n");
		nota2 = Float.parseFloat(Console.nextLine());
		Float nota3;
		System.out.println("####################################\n" + "#####            INSIRA A NOTA 3     ########\n"
				+ "####################################\n");
		nota3 = Float.parseFloat(Console.nextLine());
		Float nota4;
		System.out.println("####################################\n" + "#####            INSIRA A NOTA 4     ########\n"
				+ "####################################\n");
		nota4 = Float.parseFloat(Console.nextLine());
		return new Student(number, name, course, nota1, nota2, nota3, nota4);
	}
}
