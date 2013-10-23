package com.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class IOUtils {

	private static final String UTF8 = "UTF-8";

	public static void writeTo(final OutputStream outstream,
			InputStream inputstream) throws IOException {
		if (outstream == null) {
			throw new IllegalArgumentException("Output stream may not be null");
		}
		InputStream instream = inputstream;
		try {
			int l;
			byte[] tmp = new byte[2048];
			while ((l = instream.read(tmp)) != -1) {
				outstream.write(tmp, 0, l);
			}
		} finally {
			instream.close();
		}

	}

	public static void writeTo(final OutputStream outstream, String content)
			throws IOException {
		if (outstream == null) {
			throw new IllegalArgumentException("Output stream may not be null");
		}

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				outstream, UTF8));
		writer.write(content);
		writer.flush();
		writer.close();
		outstream.close();
	}
}
