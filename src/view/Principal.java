package view;

import java.util.concurrent.Semaphore;

import controller.Servidor;

public class Principal {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore Requisicao = new Semaphore(permissao);

		for (int id = 1 ; id <= 21; id++) {
			Thread Servidor = new Servidor (id, Requisicao);
			Servidor.start();
		}
	}       

}