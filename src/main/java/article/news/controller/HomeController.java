package article.news.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value="Employee Management System")
@RequestMapping("test")
public class HomeController {

    @ApiOperation(value = "View a list of available users", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("users")
    public ResponseEntity<List<HashMap<String, Object>>> index() {
        HashMap<String, Object> mapObject = new HashMap<>();
        mapObject.put("firstName", "Nero");
        mapObject.put("lastName", "Okiewhru");

        List<HashMap<String, Object>> arrayList = new ArrayList<>();
        arrayList.add(mapObject);
        return ResponseEntity.ok().body(arrayList);
    }

    @ApiOperation(value = "Create a user", response = Map.class)
    @PostMapping("users")
    public ResponseEntity<HashMap<String, Object>> storeUser(@RequestBody Map<String, Object> map) {
        HashMap<String, Object> mapObject = new HashMap<>();
        mapObject.put("firstName", "Nero");
        mapObject.put("lastName", "Okiewhru");
        return ResponseEntity.ok().body(mapObject);
    }

    @ApiOperation(value = "Show a user", response = Map.class)
    @PutMapping("users/{id}")
    public ResponseEntity<HashMap<String, Object>> showUser(@PathVariable Long id) {
        HashMap<String, Object> mapObject = new HashMap<>();
        mapObject.put("firstName", "Nero");
        mapObject.put("lastName", "Okiewhru");
        return ResponseEntity.ok().body(mapObject);
    }

    @ApiOperation(value = "Delete a user", response = Boolean.class)
    @DeleteMapping("users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(true);
    }


}
