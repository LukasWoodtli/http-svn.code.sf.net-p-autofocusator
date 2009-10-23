/**
 * 
 */
package autoFocusator;


/** A class that represents a task.
 * 
 * @author woodtluk
 *
 */
public class Task extends Object {

/// Creates an empty task.
public Task(){
	name = "";
	state = State.active;
}

/// Constructor that creates an named task.
public Task(String str){
	name = str;
	state = State.active;
//	priority = enumPriority.none;
//	notes = "";
//	starred = false;
}

///	The name respectively the description of the task.
private String name;

/// The state of the task.
private State state;


///	Sets the name of the task.
public void setName(String nm) {
	name = nm;
}

/// Returns the name of the task.
public String getName(){
	return name;
}

/// Sets the state of the task.
public void setState(State st){
	state = st;
}

/// Returns the state of the task.
public State getState() {
	return state;
}

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((state == null) ? 0 : state.hashCode());
	return result;
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Task other = (Task) obj;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (state == null) {
		if (other.state != null)
			return false;
	} else if (!state.equals(other.state))
		return false;
	return true;
}

/** Checks if the two tasks have the same name.
 *  If so they are the same task.
 * @param other the task to compare
 * @return true if the task is the same
 * 
 */

public boolean isSame(Task other) {
	if(other == null)
		return false;
	if(other.getName().equals(this.getName()))
			return true;
	return false;
}

}
