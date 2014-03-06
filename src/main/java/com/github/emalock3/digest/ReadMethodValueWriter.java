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
		process(dos, target, m.invoke(target));
	}
	abstract void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException;
	
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
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.write((byte[]) value);
		}
	}
	
	private static class ObjectArrayWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeUTF(Arrays.toString((Object[]) value));
		}
	}
	
	private static class BooleanWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeBoolean((Boolean) value);
		}
	}
	
	private static class ByteWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeByte((Byte) value);
		}
	}
	
	private static class CharWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeChar((Character) value);
		}
	}
	
	private static class FloatWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeFloat((Float) value);
		}
	}
	
	private static class DoubleWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeDouble((Double) value);
		}
	}
	
	private static class ShortWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeShort((Short) value);
		}
	}
	
	private static class IntWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeInt((Integer) value);
		}
	}
	
	private static class LongWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeLong((Long) value);
		}
	}
	
	private static class StringWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeUTF(value.toString());
		}
	}
	
	private static class ObjectWriter extends ReadMethodValueWriter {
		void process(DataOutputStream dos, Object target, Object value) throws ReflectiveOperationException, IOException {
			dos.writeUTF(value.toString());
		}
	}
	
}
