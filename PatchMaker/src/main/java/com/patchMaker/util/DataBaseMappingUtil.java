package com.patchMaker.util;


import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

public class DataBaseMappingUtil
{
  public static final int DEFAULT_MAX_LENGTH = 30;

  public static String abbreviateName(String inName)
  {
    return abbreviateName(inName, 30);
  }

  public static String abbreviateName(String inName, int maxLength)
  {
    String objName = null;
    String schemaName = null;
    String outName = null;
    String[] names = inName.split("\\.");

    if (names.length > 1) {
      objName = names[1];
      schemaName = names[0];
    } else {
      objName = names[0];
    }

    if (objName.length() <= maxLength) {
      outName = objName;
    }
    else
    {
      String[] tokens = splitName(objName);
      shortenName(objName, tokens, maxLength);
      outName = assembleResults(tokens);
    }

    if (StringUtils.isNotBlank(schemaName)) {
      return new StringBuilder().append(schemaName).append(".").append(outName).toString();
    }

    return outName;
  }

  private static String[] splitName(String someName)
  {
    StringTokenizer toki = new StringTokenizer(someName, "_");
    String[] tokens = new String[toki.countTokens()];
    int i = 0;
    while (toki.hasMoreTokens()) {
      tokens[i] = toki.nextToken();
      ++i;
    }
    return tokens;
  }

  private static void shortenName(String someName, String[] tokens, int maxLength) {
    int currentLength = someName.length();
    while (currentLength > maxLength) {
      int tokenIndex = getIndexOfLongest(tokens);
      String oldToken = tokens[tokenIndex];
      tokens[tokenIndex] = abbreviate(oldToken);
      currentLength -= oldToken.length() - tokens[tokenIndex].length();
    }
  }

  private static String abbreviate(String token) {
    String VOWELS = "AEIOUaeiou";
    boolean vowelFound = false;
    for (int i = token.length() - 1; i >= 0; --i)
      if (!(vowelFound))
        vowelFound = "AEIOUaeiou".contains(String.valueOf(token.charAt(i)));
      else if (!("AEIOUaeiou".contains(String.valueOf(token.charAt(i)))))
        return token.substring(0, i + 1);

    return "";
  }

  private static int getIndexOfLongest(String[] tokens) {
    int maxLength = 0;
    int index = -1;
    for (int i = 0; i < tokens.length; ++i) {
      String string = tokens[i];
      if (maxLength < string.length()) {
        maxLength = string.length();
        index = i;
      }
    }
    return index;
  }

  private static String assembleResults(String[] tokens) {
    StringBuilder result = new StringBuilder(tokens[0]);
    for (int j = 1; j < tokens.length; ++j)
      result.append("_").append(tokens[j]);

    return result.toString();
  }
}