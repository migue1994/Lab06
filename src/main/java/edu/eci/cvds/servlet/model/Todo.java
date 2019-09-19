package edu.eci.cvds.servlet.model;

public class Todo {
	
	private int userId, id;
	private String title;
	private boolean completed;

	/**
	 * Retorna el userId que aparce en la página.
	 * @return userId.
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Retorna el id ingresado por el usuario.
	 * @return id.
	 */
	public int get() {
		return id;
	}
	/**
	 * Retorna el título que aparce en la página.
	 * @return title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Retorna el valor de completed que aparce en la página.
	 * @return complted.
	 */
	public boolean getCompleted() {
		return completed;
	}
	
	/**
	 * Establece un nuevo valor para userId.
	 * @param userId es el nuvo valor del userId.
	 */
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	/**
	 * Establece un nuevo valor para id.
	 * @param id es el nuvo valor para id.
	 */
	public void setId(int id) {
		this.id=id;
	}
	
	/**
	 * Establece un nuevo valor para title.
	 * @param title es el nuevo valor de title.
	 */
	public void setTitle(String title) {
		this.title=title;
	}
	
	/**
	 * Establece un nuevo valor para completed.
	 * @param completed es el nuevo valor de completed.
	 */
	public void setCompleted(boolean completed) {
		this.completed=completed;
	}
	
}
