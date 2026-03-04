package cloud.lambda.qrcode;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;
import java.util.Base64.Encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCode {
	MultiFormatWriter writer;
	ByteArrayOutputStream baos;
	Encoder encoder;
	Map<EncodeHintType, Object> hints;

	public QRCode() {
		writer = new MultiFormatWriter();
		baos = new ByteArrayOutputStream();
		encoder = Base64.getEncoder();
		hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	}
	
	String getQRCode(String data) throws Exception {
		BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 300, 300, hints);
		MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
		byte[] imageData = baos.toByteArray();
		baos.reset();
		return encoder.encodeToString(imageData);
	}
}
