package ui.util;

/*
 * StandardInputReader.java
 *
 * Copyright (C) 2008 Mamezou Co., Ltd. All rights reserved.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * StandardInputReaderクラスは、コンソールから入力されたデータを取得するためのユーティリティクラスです.<br>
 * それぞれのメソッドの引数には入力を促すための文字列を指定することが可能です。<br>
 * <br>
 * <b>文字列データを取得する場合 {@link #getInputString(String)} メソッドを使用します</b><br>
 * 使用例 :
 * <code>String stringData = StandardInputReader.getInputString("文字列を入力して下さい：");</code>
 * <br>
 * <br/>
 * <b>整数の数値データを取得する場合 {@link #getInputInt(String)} メソッドを使用します</b><br/>
 * 使用例 : <code>int intData = StandardInputReader.getInputInt("数値を入力して下さい："); </code>
 * <br/>
 * <br/>
 * <b>小数の数値データを取得する場合 {@link #getInputDouble(String)} メソッドを使用します</b><br/>
 * 使用例 : <code>double doubleData = StandardInputReader.getInputDouble("数値を入力して下さい："); </code>
 */
public class StandardInputReader {

  /**
   * ユーザの入力した文字データを取得します。
   *
   * @param message 表示するメッセージ
   * @return ユーザの入力した文字データ。文字が入力されなかった場合、長さ0の文字列が返却されます。
   */
  public static String getInputString(String message) {
    System.out.print(message);
    String inputString = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(
            System.in));
    try {
      inputString = reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException("入力時にエラーが発生しました。", e);
    }
    return inputString;
  }

  /**
   * ユーザの入力した数値データ(整数)を取得します。
   *
   * @param message 表示するメッセージ
   * @return ユーザの入力した数値データ(整数)
   * @throws NumberFormatException int型に変換できない値(アルファベット、long型の範囲の整数、小数など)が入力された場合
   */
  public static int getInputInt(String message) throws NumberFormatException {
    return Integer.parseInt(getInputString(message));
  }

  /**
   * ユーザの入力した数値データ(浮動小数点)を取得します。
   *
   * @param message 表示するメッセージ
   * @return ユーザの入力した数値データ(浮動小数点)
   * @throws NumberFormatException double型に変換できない値(アルファベットなど)が入力された場合
   */
  public static double getInputDouble(String message) throws NumberFormatException {
    return Double.parseDouble(getInputString(message));
  }
}