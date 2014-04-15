package interfaces;

public interface IPrototyp {

	public abstract Object clone();
}

//just instantiate the object once in the client and then just clone the next ones if you need