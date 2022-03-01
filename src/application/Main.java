package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.Student;

public class Main {
	static final Scanner Console = new Scanner(System.in);
	static StudentDao studentDao = DaoFactory.createStudentDao();
	static List<Student> list;

	public static void main(String args[]) {
		while (screen() != 0);
	}

	public static Integer screen() {
		Integer option;
		System.out.println("####################################\n" + "#####  1 - INSERIR ALUNO   #########\n"
				+ "#####  2 - LISTAR ALUNOS     #######\n" + "#####  0 - ENCERRAR PROGRAMA #######\n"
				+ "####################################\n");
		option = Integer.parseInt(Console.nextLine());
		switch (option) {
		case 0:
			return option;
		case 1:
			Student newStudent = insert();
			studentDao.insert(newStudent);
			System.out.println("Inserted! New id = " + newStudent.getNumber());
			break;
		case 2:
			findAll();
			break;
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

	public static void findAll() {
		int total_students = 0;
		int approved_students = 0;
		int reproved_students = 0;
		int recovery_students = 0;
		float general_average = 0.0f;

		System.out.println();
		list = studentDao.findAll();

		for (Student s : list) {
			Float media = (s.getNota1() + s.getNota2() + s.getNota3() + s.getNota4()) / 4;
			general_average += media;
			
			String situacao = (media >= 7) ? "APROVADO" : (media < 3) ? "REPROVADO" : "RECUPERAÇÃO";
			if(situacao.equalsIgnoreCase("APROVADO"))
				approved_students++;
			else if(situacao.equalsIgnoreCase("REPROVADO"))
				reproved_students++;
			else
				recovery_students++;
			
			System.out.printf(
					"Aluno %d: %s\n" + "Curso: %s\n" + "Notas: %2.2f %2.2f %2.2f %2.2f\n"
							+ "Situação: %s com média %f\n\n",
					s.getNumber(), s.getName(), s.getCourse(), s.getNota1(), s.getNota2(), s.getNota3(), s.getNota4(),
					situacao, media);
			total_students++;
		}
		general_average /= total_students;
		
		System.out.printf("Número total de alunos: %d\n"
				+ "Número de alunos aprovados: %d\n"
				+ "Número de alunos em recuperação: %d\n"
				+ "Número de alunos reprovados: %d\n"
				+ "Média geral da turma: %2.2f\n", total_students, approved_students, recovery_students, reproved_students, general_average);
	}
}
