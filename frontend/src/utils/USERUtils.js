import RESTUtils from "@/utils/RESTUtils";
import {tr} from "vuetify/locale";

const USERUtils = {
  async validateUser(id, shortname) {
    const employee = {
      id: id,
      shortName: shortname
    }
    let response = await RESTUtils.sendPostRequest("/employee/validate", employee)
    console.log(response.data)
    return response.data
  }
}

export default USERUtils;
