package by.itacademy.javaenterprise.goralchuk.controller;

import by.itacademy.javaenterprise.goralchuk.dto.ComplainsDto;;
import by.itacademy.javaenterprise.goralchuk.entity.Complains;
import by.itacademy.javaenterprise.goralchuk.entity.Doctor;
import by.itacademy.javaenterprise.goralchuk.entity.security.User;
import by.itacademy.javaenterprise.goralchuk.exception.NoFoundException;
import by.itacademy.javaenterprise.goralchuk.service.ComplainsService;
import by.itacademy.javaenterprise.goralchuk.service.DoctorService;
import by.itacademy.javaenterprise.goralchuk.util.ChooseDoctor;
import by.itacademy.javaenterprise.goralchuk.util.MapperUtil;
import by.itacademy.javaenterprise.goralchuk.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/complains")
public class ComplainController {

    @Autowired
    private ComplainsService complainsService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "")
    public ResponseEntity<List<ComplainsDto>> findAllComplains(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "resultTreatment") String sortBy) {
        List<Complains> complains = complainsService.findAll(pageNo, pageSize, sortBy);
        List<ComplainsDto> complainsListDto = MapperUtil.convertList(complains, this::convertToComplainDto);
        if (complains.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(complainsListDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ComplainsDto> findComplainById(
            @PathVariable("id") Long id) {
        Optional<Complains> complains = complainsService.findById(id);
        if (complains.isEmpty()) {
            throw new NoFoundException("No found complain " + id);
        }
        return new ResponseEntity<>(convertToComplainDto(complains.get()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ComplainsDto> addDoctorToComplain(
            @PathVariable("id") Long id,
            @RequestBody Message message
    ) {
        Optional<Complains> addData = complainsService.findById(id);
        Optional<Doctor> findDoc = doctorService.findById(message.getMessage());
        if (addData.isEmpty() || findDoc.isEmpty()) {
            throw new NoFoundException("No found complain " + id);
        }
        addData.get().setDoctor(findDoc.get());
        complainsService.saveOrUpdate(addData.get());
        return new ResponseEntity<>(convertToComplainDto(addData.get()), HttpStatus.CREATED);
    }


    private ComplainsDto convertToComplainDto(Complains complains) {
        return modelMapper.map(complains, ComplainsDto.class);
    }
}
