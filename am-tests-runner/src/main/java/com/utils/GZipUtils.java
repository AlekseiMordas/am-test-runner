package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;

public class GZipUtils {

	public static final String GZIP_CONTENT = "gzip";

	public static byte[] compress(String input) throws IOException {
		byte[] bytes = input.getBytes();
		if (input == null || input.length() == 0) {
			return bytes;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(input.getBytes(), 0, bytes.length);
		gzip.close();

		return out.toByteArray();
	}

	public static String decompress(byte[] bytes) throws IOException {		
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		GZIPInputStream gzis = new GZIPInputStream(bais);
		String decompressed = new String(IOUtils.toByteArray(gzis));
		IOUtils.closeQuietly(gzis);
		IOUtils.closeQuietly(bais);		
		return decompressed;
	}

}
