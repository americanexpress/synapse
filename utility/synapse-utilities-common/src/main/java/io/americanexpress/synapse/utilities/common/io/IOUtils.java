/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.utilities.common.io;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * {@code IOUtils} is used for IO operations.
 */
public class IOUtils {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(IOUtils.class);

    /**
     * Default constructor creates a new instance of IOUtils with default values.
     */
    private IOUtils() {
        // A class containing only static members is a utility class that requires a private default constructor
    }

    /**
     * Read the contents of a file to a single string.
     *
     * @param fileName any file name along the class path, including its file extension
     * @return the contents of a file as a single string
     */
    public static String readFileToAString(String fileName) {
        String text;
        try (Reader reader = new InputStreamReader(new ClassPathResource(fileName).getInputStream())){
            text = FileCopyUtils.copyToString(reader);
        } catch(IOException ioException) {
            throw new ApplicationServerException(ioException);
        }

        return text;
    }

    /**
     * Write a string to a file.
     * If the file does not exist, it will be created first, then the text will be written.
     *
     * Note: if the file already contains content, it will be overridden.
     *       If you need to append to a file, consider using {@link #appendStringToAFile(String, String)}.
     *
     * @param fileName to which the text will be written
     * @param text to be written to the file
     * @param openOptions if null or not present, the text will be written normally
     */
    static void writeStringToAFile(String fileName, String text, OpenOption... openOptions) {

        // Get the file path
        Path path = Paths.get(fileName);
        String messageFormat = MessageFormat.format("File path: {0}", path.toFile().getAbsolutePath());
        LOGGER.info(messageFormat);

        // Write the string to the file
        // Note: if openOptions is null, Files.write(path, text.getBytes(), null) successfully behaves identically to Files.write(path, text.getBytes())
        try {
            Files.write(path, text.getBytes(), openOptions);
        } catch (IOException ioException) {
            throw new ApplicationServerException(ioException);
        }
    }

    /**
     * Append a string to a file.
     * If the file does not exist, it will be created first, then the text will be written.
     * Otherwise if the file already exists, then the text will be appended to the end of the file.
     *
     * Note: if  you need to override a file, consider using {@link #writeStringToAFile(String, String, OpenOption...)}.
     *
     * @param fileName to which the text will be written
     * @param text to be written to the file
     */
    public static void appendStringToAFile(String fileName, String text) {
        // Write to a file, enabling CREATE and APPEND options
        // CREATE will check if the file exists and if it does not, it will create the file
        // APPEND will append the text to the end of the file
        writeStringToAFile(fileName, text, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * Get strings from a file as a list.
     *
     * @param fileName any file name along the class path
     * @return the strings from a file as a list
     */
    public static List<String> getStringsFromFile(String fileName) {

        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException exception) {
            //This one will be handled by the handlesApplicationException method first if, when cause != null, since cause is the exception that we pass here.
            throw new ApplicationServerException(exception);
        }
    }

    /**
     * Compresses bytes of data.
     *
     * @param data any bytes of data
     * @return the compressed bytes
     */
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        byte[] compressedBytes = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            byte[] buffer = new byte[1024];
            int count;
            while (!deflater.finished()) {
                count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            compressedBytes = outputStream.toByteArray();
        } catch (IOException exception) {
            LOGGER.catching(exception);
        }
        return compressedBytes;
    }

    /**
     * Decompresses bytes of data.
     *
     * @param data any bytes of data
     * @return the decompressed bytes
     */
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        byte[] compressedBytes = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            byte[] buffer = new byte[1024];
            int count = 0;
            while (!inflater.finished()) {
                count = getCount(inflater, buffer);
                outputStream.write(buffer, 0, count);
            }
            compressedBytes = outputStream.toByteArray();
        } catch (IOException exception) {
            LOGGER.catching(exception);
        }
        return compressedBytes;
    }

    private static int getCount(Inflater inflater, byte[] buffer) {
        int count = 0;
        try {
            count = inflater.inflate(buffer);
        } catch (DataFormatException exception) {
            LOGGER.catching(exception);
        }
        return count;
    }

}
