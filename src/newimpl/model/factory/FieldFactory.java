package newimpl.model.factory;

import newimpl.model.BombField;
import newimpl.model.EmptyField;
import newimpl.model.Field;

public class FieldFactory {

	public static Field getFieldObj(String str) {
		switch (str.substring(0, 1)) {
		case "_":
			return new EmptyField(str.substring(1).equals("C") ? false : true);
		case "*":
			return new BombField();
		default:
			return new EmptyField(Integer.parseInt(str.substring(0, 1)), str.substring(1).equals("O") ? true : false,
					str.substring(1).equals("F") ? true : false);
		}
	}
	
	public static String getFieldStr(Field field) {
		return field.toString();
	}

}
