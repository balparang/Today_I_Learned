package org.example.ch03_polymorphism_and_abstract_type;

public class SocketDataReader implements ByteSource{

	@Override
	public byte[] read() {
		throw new UnsupportedOperationException();
	}

}
