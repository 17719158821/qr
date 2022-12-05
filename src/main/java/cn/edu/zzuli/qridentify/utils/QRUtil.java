package cn.edu.zzuli.qridentify.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import xyz.hashdog.dm.bean.Text;
import xyz.hashdog.dm.util.ImageUtil;

/**
 * 二维码以及图片合成工具类
 *
 * @author wysnxzm
 */
public class QRUtil {

    /**
     * 生成二维码
     *
     * @param contents 二维码内容
     * @param width    图片宽度
     * @param height   图片高度
     * @param hints    二维码相关参数
     * @return BufferedImage对象
     * @throws WriterException 编码时出错
     * @throws IOException     写入文件出错
     */
    public static BufferedImage enQRCode(String contents, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        //生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage enQRCode(String contents, int width, int height, int margin) throws WriterException {
        //定义二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //容错级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //边距
        hints.put(EncodeHintType.MARGIN, margin);
        return enQRCode(contents, width, height, hints);
    }


    /**
     * 将图片绘制在背景图上
     *
     * @param backgroundImage 背景图
     * @param zxingImage      图片
     * @param x               图片在背景图上绘制的x轴起点
     * @param y               图片在背景图上绘制的y轴起点
     * @return
     * @throws IOException
     */
    public static BufferedImage drawImage(BufferedImage backgroundImage, BufferedImage zxingImage, int x, int y) throws IOException {
        Objects.requireNonNull(backgroundImage, ">>>>>背景图不可为空");
        Objects.requireNonNull(zxingImage, ">>>>>二维码不可为空");
        //二维码宽度+x不可以超过背景图的宽度,长度同理
        if ((zxingImage.getWidth() + x) > backgroundImage.getWidth() || (zxingImage.getHeight() + y) > backgroundImage.getHeight()) {
            throw new IOException(">>>>>二维码宽度+x不可以超过背景图的宽度,长度同理");
        }

        //合并图片
        Graphics2D g = backgroundImage.createGraphics();
        g.drawImage(zxingImage, x, y,
                zxingImage.getWidth(), zxingImage.getHeight(), null);
        return backgroundImage;
    }


    public static void main(String[] args) {
        //二维码宽度
        int width = 450;
        //二维码高度
        int height = 450;
        //二维码内容
        String contcent = "http://124.220.156.201:8801/result?certificateCode=CD2022102022021";
        BufferedImage zxingImage = null;
        try {
            //二维码图片流
            zxingImage = QRUtil.enQRCode(contcent, width, height, 7);
            Font f = new Font("Times New Roman", Font.PLAIN, 30);
            BufferedImage saver = QRUtil.drawString(zxingImage, "ZZULI6786868767810462", f, width, height);
            InputStream saveInout = QRUtil.bufferedImageToInputStream(saver);
            QRUtil.saveFile(saveInout, "E:\\WorkSpace\\IdeaWorkSpace\\qr\\cache\\QR.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文字绘制在背景图上
     *
     * @param backgroundImage 背景图
     * @return
     * @throws IOException
     */
    public static BufferedImage drawString(BufferedImage backgroundImage, String text, Font font, int width, int height) {
        Graphics2D g2d = backgroundImage.createGraphics();
//        设置字体
        g2d.setFont(font);
//        设置背景颜色
        g2d.setColor(new Color(0, 0, 0));
//        平滑锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
//        得到字体矩阵
        FontMetrics fm2 = g2d.getFontMetrics();
//        得到字体的边框对象
        Rectangle2D rec2 = fm2.getStringBounds(text, g2d);
//        得到绘制的字宽度
        int cx = (int) Math.ceil(rec2.getWidth());
//        得到绘制的字长度
        int cy = (int) Math.ceil(rec2.getHeight());
        g2d.dispose();
//        居中字体,得到绘制的起始位置
        width = (width-cx) / 2;
//        置于底部
        height = height - 10 - cy;
        ImageUtil.DrawString(backgroundImage, text, width, height, font, "#000000");
        return backgroundImage;
    }

    /**
     * backgroundImage 转换为输出流
     *
     * @param backgroundImage 图片流
     * @param format          图片后缀
     * @return
     * @throws IOException
     */
    public static InputStream bufferedImageToInputStream(BufferedImage backgroundImage, String format) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try (
                ImageOutputStream
                        imOut = ImageIO.createImageOutputStream(bs)) {
            ImageIO.write(backgroundImage, format, imOut);
            InputStream is = new ByteArrayInputStream(bs.toByteArray());
            return is;
        }
    }

    public static InputStream bufferedImageToInputStream(BufferedImage backgroundImage) throws IOException {
        return bufferedImageToInputStream(backgroundImage, "png");
    }

    /**
     * 保存为文件
     *
     * @param is       输入流
     * @param fileName 保存的图片路径和文件名
     * @throws IOException
     */
    public static void saveFile(InputStream is, String fileName) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(is);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        }
    }


    public static void generateQRFile(String content, String img_text, String dist_path) {
        //二维码宽度
        int width = 450;
        //二维码高度
        int height = 450;
        //二维码
        BufferedImage zxingImage = null;
        try {
//            生成二维码
            zxingImage = QRUtil.enQRCode(content, width, height, 7);
            Font f = new Font("Times New Roman", Font.PLAIN, 30);
//            将编号绘制入二维码底部居中
            BufferedImage saver = QRUtil.drawString(zxingImage, img_text, f, width, height);
            InputStream saveInout = QRUtil.bufferedImageToInputStream(saver);
//            二维码保存到本地缓存
            QRUtil.saveFile(saveInout, dist_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }
}
