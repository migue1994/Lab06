package edu.eci.cvds.servlet.model;

import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "guessBean")
@SessionScoped
public class Guess {
	
	private final int maximo=100000;
	private final int penal=10000;
	private final int rango=5;
	private int premio;
	private int guessNum,intentos;
	private boolean winner,gameOver;
	private int guessUser;
	private String estado;
	private ArrayList<Integer> listaIntentos;
	
	
	public Guess() {
		restart();
	}
	
	/**
	 * Calcula un número aleatorio entre 1 y 1100000000
	 * @return un número entero aleatorio
	 */
	private int randomNum() {
		Random random = new Random(System.currentTimeMillis());
		int num = random.nextInt(1100000000)+1;
		random.setSeed(System.currentTimeMillis());	
		return num;
	}
	
	/**
	 * Evalúa si el número dado por el usuario es o no igual al generado por el juego.
	 */
	public void guess() {
		if(!this.gameOver && !this.winner) {
			if(guessUser!=this.guessNum) {
				int resta=this.premio-penal;
				if(resta>=0) {
					this.premio-=this.penal;
					if(this.premio==0) this.gameOver=true;
				}
			}
			else {
				this.gameOver=true;
				this.winner=true;
			}
			this.listaIntentos.add(this.intentos,guessUser);
			this.intentos++;
			
		}
	}
	
	
	/**
	 * Reinicia el juego.
	 */
	public void restart() {
		this.premio=maximo;
		this.guessNum=randomNum();
		this.intentos=0;
		this.winner=false;
		this.gameOver=false;
		this.guessUser=0;
		this.setEstado("En juego");
		this.listaIntentos=new ArrayList<Integer>();
		inicializarLista(this.listaIntentos,(int)(maximo/penal));
	}
	
	/**
	 * Inicializa un ArrayList de enteros.
	 * @param lista es el ArrayList de enteros.
	 * @param n es hasta donde se inicializar la lista.
	 */
	private void inicializarLista(ArrayList<Integer> lista, int n) {
		for(int i=0;i<n;i++) lista.add(i,0);
	}
	
	/**
	 * Devuelve el premio del usuario durante el juego.
	 * @return el valor del puntaje del usuario.
	 */
	public int getPremio() {
		return this.premio;
	}

	/**
	 * Actualiza el valor del premio del usuario por el dado.
	 * @param premio es el valor del nuevo puntaje del usuario.
	 */
	public void setPremio(int premio) {
		this.premio=premio;
	}
	
	/**
	 * Devuelve el valor ingresado por el usuaro.
	 * @return el número ingresado por el usuaro.
	 */
	public int getGuessUser() {
		return this.guessUser;
	}
	
	/**
	 * Actualiza el valor del número ingresado por el usuario por el valor dado.
	 * @param guessUser es el nuevo valor ingresado por el usuario.
	 */
	public void setGuessUser(int guessUser) {
		this.guessUser=guessUser;
	}
	
	/**
	 * Devuelve el valor que representa al número de intentos realizados por el usuario para adivinar
	 * el número .
	 * @return el número de intentos realizados por el usuario en el juego.
	 */
	public int getIntentos() {
		return this.intentos;
	}
	
	/**
	 * Actualiza el valor del número de intentos por el valor dado.
	 * @param intentos es el nuevo número de intentos del usuario por tratar de adivinar el número.
	 */
	public void setIntentos(int intentos) {
		this.intentos=intentos;
	}
	
	/**
	 * Devuelve el estado en el que se encuenta el juego.
	 * Si el usuario ganó: "Has ganado".
	 * Si el usuario perdió: "Has perdido".
	 * Otro (cuando está jugando): "En juego".
	 * @return una cadena que representa el estado del juego.
	 */
	public String getEstado() {
		if(!gameOver) {
			this.estado="En juego";
		}
		else {
			if(winner) this.estado="Has ganado";
			else this.estado="Has perdido";
		}
		return this.estado;
	}
	
	/**
	 * Actualiza el estado del juego.
	 * @param estado es el nuevo estado del juego.
	 */
	public void setEstado(String estado) {
		this.estado=estado;
	}
	
	/**
	 * Devuelve el valor del número que el usuario debe adivinar.
	 * @return el número que se debe adivinar durante el juego.
	 */
	public int getGuessNum() {
		return this.guessNum;
	}
	
	/**
	 * Actualiza el valor del número que se debe adivnar durante el juego.
	 * @param guessNum es el nuevo valor que el usuario debe adivinar.
	 */
	public void setGuessNum(int guessNum) {
		this.guessNum=guessNum;
	}
	
	/**
	 * Devuelve una lista con los últimos intentos realizados por el usuario, según el rango
	 * establecido al principio.
	 * @return una lista con los últimos intentos realizados por el usuario.
	 */
	public ArrayList<Integer> getListaIntentos(){
		ArrayList<Integer>l=new ArrayList<Integer>();
		int desde,hasta,j=0;
		
		if(this.intentos<this.rango) {
			desde=0;hasta=this.rango;
		}
		else {
			desde=this.intentos-this.rango;hasta=this.rango+desde;
		}
		for(int i=desde;i<hasta;i++) {
			l.add(j,listaIntentos.get(i));
			j++;
		}
		
		return l;
	}
	
	/**
	 * Actualiza el contenido de la lista que contiene los últimos intentos realizados por el usuario.
	 * @param listaIntentos Es la nueva lista que contiene los valores de los últimos intentos realizados
	 * por el usuario.
	 */
	public void setListaIntentos(ArrayList<Integer> listaIntentos){
		this.listaIntentos=listaIntentos;
	}
	
}





