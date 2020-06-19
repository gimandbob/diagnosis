package covidtest;

import covidtest.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    DiagnosisRepository diagnosisRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSent_Diagnosis(@Payload Sent sent){

        if(sent.isMe()){
            Diagnosis diagnosis = new Diagnosis(sent);

            diagnosisRepository.save(diagnosis);
            System.out.println("##### listener Diagnosis : " + sent.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSendCancelled_DiagnosisCancel(@Payload SendCancelled sendCancelled) throws Exception {

        if(sendCancelled.isMe()){
            Diagnosis diagnosis = diagnosisRepository.findByInspectionId(sendCancelled.getInspectionId())
                    .orElseThrow(() -> new Exception("diagnosis not found"));
            diagnosis.setStatus("CANCELLED");
            diagnosisRepository.save(diagnosis);
            System.out.println("##### listener DiagnosisCancel : " + sendCancelled.toJson());
        }
    }

}
