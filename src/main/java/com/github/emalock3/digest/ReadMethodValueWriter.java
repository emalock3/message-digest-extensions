package com.github.emalock3.digest;

import java.beans.PropertyDescriptor;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

abstract class ReadMethodValueWriter {
	void write(DataOutputStream dos, Object target, PropertyDescriptor pd) throws ReflectiveOperationException, IOException {
		Method m = pd.getReadMethod();
		if (m.isAccessible()) {
			try {
				m.setAccessible(true);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			}
		}
	}
	abstract void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException;
	
	static ReadMethodValueWriter createWriter(PropertyDescriptor pd) {
		Class<?> type = pd.getPropertyType();
		if (type == byte[].class) {
			return new ByteArrayWriter();
		} else if (type == Boolean.TYPE) {
			return new BooleanWriter();
		} else if (type == Byte.TYPE) {
			return new ByteWriter();
		} else if (type == Character.TYPE) {
			return new CharWriter();
		} else if (type == Float.TYPE) {
			return new FloatWriter();
		} else if (type == Double.TYPE) {
			return new DoubleWriter();
		} else if (type == Short.TYPE) {
			return new ShortWriter();
		} else if (type == Integer.TYPE) {
			return new IntWriter();
		} else if (type == Long.TYPE) {
			return new LongWriter();
		} else if (type == String.class) {
			return new StringWriter();
		} else if (type.isArray()) {
			return new ObjectArrayWriter();
		} else {
			return new ObjectWriter();
		}
	}

	private static class ByteArrayWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.write((byte[]) m.invoke(target));
		}
	}
	
	private static class ObjectArrayWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeUTF(Arrays.toString((Object[]) m.invoke(target)));
		}
	}
	
	private static class BooleanWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeBoolean((Boolean) m.invoke(target));
		}
	}
	
	private static class ByteWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeByte((Byte) m.invoke(target));
		}
	}
	
	private static class CharWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeChar((Character) m.invoke(target));
		}
	}
	
	private static class FloatWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeFloat((Float) m.invoke(target));
		}
	}
	
	private static class DoubleWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeDouble((Double) m.invoke(target));
		}
	}
	
	private static class ShortWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeShort((Short) m.invoke(target));
		}
	}
	
	private static class IntWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeInt((Integer) m.invoke(target));
		}
	}
	
	private static class LongWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeLong((Long) m.invoke(target));
		}
	}
	
	private static class StringWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeUTF(m.invoke(target).toString());
		}
	}
	
	private static class ObjectWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Method m) throws ReflectiveOperationException, IOException {
			dos.writeUTF(m.invoke(target).toString());
		}
	}
	
}
