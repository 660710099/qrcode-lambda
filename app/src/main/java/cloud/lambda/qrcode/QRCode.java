package cloud.lambda.qrcode;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCode {
	MultiFormatWriter writer = new MultiFormatWriter();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	Encoder encoder = Base64.getEncoder();
	String getQRCode(String data) throws Exception {
		BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 300, 300);
		MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
		byte[] imageData = baos.toByteArray();
		baos.reset();
		return encoder.encodeToString(imageData);
	}
}
