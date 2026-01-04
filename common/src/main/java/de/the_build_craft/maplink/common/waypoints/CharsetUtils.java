/*
 *    This file is part of the Map Link mod
 *    licensed under the GNU GPL v3 License.
 *
 *    Copyright (C) 2025  Leander Knüttel and contributors
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.the_build_craft.maplink.common.waypoints;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Utility class to handle character encoding issues with Xaero's Map.
 * 
 * Xaero's Map has a bug where it treats UTF-8 strings as the system's default charset
 * (e.g., GBK on Chinese systems). This causes CJK characters to display as gibberish.
 * 
 * The workaround is to re-encode UTF-8 strings as ISO-8859-1 before passing them to Xaero.
 * When Xaero treats these as the system charset and converts back, the original UTF-8
 * characters will display correctly.
 *
 * @author Leander Knüttel
 * @version 04.01.2026
 */
public class CharsetUtils {
    /**
     * Converts a UTF-8 string to a format that Xaero's Map can correctly interpret.
     * This method re-encodes the string to work around Xaero's charset handling bug.
     * 
     * @param name The original UTF-8 string
     * @return The re-encoded string that Xaero can correctly display
     */
    public static String encodeForXaero(String name) {
        if (name == null) {
            return null;
        }
        
        try {
            // Re-encode UTF-8 bytes as ISO-8859-1 to work around Xaero's charset bug
            // When Xaero treats this as the system charset (e.g., GBK) and converts back,
            // it will correctly display the original UTF-8 characters
            byte[] utf8Bytes = name.getBytes(StandardCharsets.UTF_8);
            return new String(utf8Bytes, StandardCharsets.ISO_8859_1);
        } catch (Exception e) {
            // If encoding fails, return the original string
            return name;
        }
    }
}
