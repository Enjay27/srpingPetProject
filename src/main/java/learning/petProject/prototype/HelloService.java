package learning.petProject.prototype;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    HelloJpaRepository helloJpaRepository;

    public void deleteHello(Long id) {
        Hello hello = helloJpaRepository.findById(id).get();
        helloJpaRepository.delete(hello);
    }
}
