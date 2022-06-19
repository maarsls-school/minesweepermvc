package newimpl.model.factory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.base.Splitter;

import newimpl.model.Field;
import newimpl.model.Playground;

public class FieldSerializer {
	public static void save(String fPath, Field[][] matrix) throws IOException {
		System.out.println("save");
		StringBuilder builder = new StringBuilder();
		for (int height = 0; height < 15; height++) {
			for (int length = 0; length < 15; length++) {
				builder.append(FieldFactory.getFieldStr(matrix[height][length]));
			}
			builder.append("\n");
		}

		Files.write(Paths.get(fPath), builder.toString().getBytes());
	}

	public static Field[][] load(String fPath) throws IOException {
		Field[][] matrix = new Field[15][15];
		int height = 0;
		int length = 0;

		List<String> lines = Files.readAllLines(Paths.get(fPath));
		for (String string : lines) {
			for (final String token : Splitter.fixedLength(2).split(string)) {
				matrix[height][length] = FieldFactory.getFieldObj(token);
				length++;
			}
			length = 0;
			height++;
		}

//		Playground.toStringByField(matrix);
		return matrix;
	}
}
