package bsuedu.golovkov.diplom.service.impl;

import bsuedu.golovkov.diplom.service.HService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HServiceImpl implements HService {

    @Override
    public String getH() {
        return "h";
    }
}
