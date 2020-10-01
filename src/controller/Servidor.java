package controller;

import java.util.concurrent.Semaphore;

public class Servidor extends Thread {
	private int id;
	private Semaphore Requisicao;
	private int tempoCalculo;
	private int numTransacoes;

	public Servidor(int id, Semaphore requisicao) {
		this.id = id;
		this.Requisicao = requisicao;
	}

	@Override
	public void run() {
		if (id % 3 == 0 || id % 3 == 2) {
			numTransacoes = 3;
		} else {
			numTransacoes = 2;
		}
		for (int i = 0; i < numTransacoes; i++) {
			RealizarCalculos();
		}

		try {
			Requisicao.acquire();
			RealizarTransacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			Requisicao.release();
		}

		System.out.println("Termino thread #" + id);

	}

	private void RealizarCalculos() {
		System.out.println("Thread id #" + id + " esta realizando calculos...");
		if (id % 3 == 0) {
			tempoCalculo = (int) ((Math.random() * 1001) + 1000);

			try {
				sleep(tempoCalculo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (id % 3 == 1) {
			tempoCalculo = (int) ((Math.random() * 801) + 200);
			try {
				sleep(tempoCalculo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else if (id % 3 == 2) {
			tempoCalculo = (int) ((Math.random() * 1001) + 500);
			try {
				sleep(tempoCalculo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Tempo de calculo #" + id + " invalido!");
		}

	}

	private void RealizarTransacao() {
		System.out.println("Thread id #" + id + " esta realizando transação para o BD...");
		if (id % 3 == 0 || id % 3 == 2) {
			try {
				sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else if (id % 3 == 1) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Transação #" + id + " invalido!");
		}

	}
}
