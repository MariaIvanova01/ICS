package com.ICS.ImageClassifier;

import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ImageClassifierApplicationTests {

	@MockBean
	private ImageClassificationWrapper imageClassificationWrapper;
	@Test
	void contextLoads() {
	}

}
