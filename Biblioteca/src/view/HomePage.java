package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.DAO;
import model.Livro;

public class HomePage {
	
	public void HomePageBiblioteca()  {
		Livro livro = null;
		Scanner sc = new Scanner(System.in);
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DAO rps = new DAO();
		boolean rodando = true;
		
		while(rodando) {
			System.out.println("\t Bem vindo");
			System.out.println("[1] -> Cadastrar Livro no Sistema");
			System.out.println("[2] -> Buscar Livro via ID");
			System.out.println("[3] -> Listar Livros cadastrados");
			System.out.println("[4] -> Atualizar Dados de Livros /OBS:SE O ID PASSADO COMO PARAMETRO NÃO EXISTIR , OS DADOS INFORMADOS SERÃO INSERIDOS NO SISTEMA");
			System.out.println("[5] -> Deletar Livros do Sistema /OBS:TODOS OS DADOS SERÃO PERDIDOS");
			System.out.println("[0] -> Encerrar Programa");
			System.out.print("Informe sua opção: ");
			int opcao = sc.nextInt();
			sc.nextLine();
			
			switch(opcao) {
			case 1:
				 livro = new Livro();
				System.out.println("\t # ABA DE CADASTRO DE LIVROS #");
				
				System.out.print("Titulo: ");
				livro.setTitulo(sc.nextLine());
				
				System.out.print("Autor: ");
				livro.setAutor(sc.nextLine());
				
				System.out.print("ISBN: ");
				livro.setIsbn(sc.nextLine());
				
				//setando dataAtual
				livro.setData(sdf.format(data));
				
				System.out.print("Número de Paginas: ");
				livro.setNumPaginas(sc.nextInt());
				//cleaning buffer
				sc.nextLine();
				
				System.out.print("Valor: ");
				livro.setValor(sc.nextDouble());
				
				boolean inseriu ;
				inseriu = rps.Insert(livro);
				
				if(inseriu) {
					System.out.println("Inseriu");
				}else {
					System.out.println("Falha na Inserção");
				}
				
				break;
				
			case 2:
				System.out.println("\t ABA DE BUSCA POR LIVRO VIA ID");
				System.out.print("Informe o Id do Livro que você deseja realizar a busca: ");
				int id= sc.nextInt();
				sc.nextLine();
				livro = new Livro();
				livro = rps.searchById(id);
				
				if(livro != null) {
					System.out.println("Livro encontrado com Sucesso !!");
					System.out.print(livro.toString());
				}else {
					System.out.println("Livro não encontrado no sistema");
				}
				break;
				
			case 3:
				System.out.println("\t ABA DE LISTAGEM");
				ArrayList<Livro> livros = new ArrayList<>();
				livros = rps.list();
				if(livros.size() == 0) {
					System.out.println("A LISTA ESTÁ VAZIA");
				}else {
					System.out.println("\t LISTAGEM ");
				for (Livro livroTs : livros) {
					System.out.println(livroTs.toString());
				}
				}
				break;
				
			case 4:
				livro = new Livro();
				System.out.println("\t ABA DE ATUALIZAÇÃO DE CADASTRO");
				System.out.print("Informe o Id do Livro que você deseja realizar a atualização: ");
				livro.setId(sc.nextInt());
				sc.nextLine();
				System.out.print("Infome o Novo titulo: ");
				livro.setTitulo(sc.nextLine());
				System.out.print("Informe o Novo autor: ");
				livro.setAutor(sc.nextLine());
				System.out.print("Informe o novo ISBN: ");
				livro.setIsbn(sc.nextLine());
				System.out.print("Informe o novo Valor: ");
				livro.setValor(sc.nextDouble());
				livro.setData(sdf.format(data));
				System.out.print("Informe o número de Paginas: ");
				livro.setNumPaginas(sc.nextInt());
				sc.nextLine();
				boolean atualizou = rps.update(livro);
				if(atualizou) {
					System.out.println("ATUALIZOU");
				}else {
					System.out.println("FALHA AO ATUALIZAR, INSERINDO NOVO DADO NO SISTEMA");
				}
				
				break;
				
			case 5:
				System.out.println("ABA DE DELETE *TODOS OS DADOS SERÃO PERDIDOS* ");
				System.out.print("informe o Id do livro que você deseja remover: ");
				id = sc.nextInt();
				
				livro = rps.searchById(id);
				boolean deletou = rps.delete(id);
				if(deletou == false) {
					System.out.println("Deletou");
				}else {
					System.out.println("Falhou");
				}
				break;
				
			case 0:
				System.out.println("Progama Finalizado Até a proxima... ");
				rodando  = false;
				break;
				
			default:
			System.out.println("OPCAO INVALIDA");
			break;
			}
			
		}
	}
	
}
