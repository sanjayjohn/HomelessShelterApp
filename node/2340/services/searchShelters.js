function searchByName(shelters, name) {
	console.log(name);
	let filtered = {};

	let regString = "";
	let searchArr = name.split(" ");
	
	searchArr.forEach(word => {
		regString = regString + word + "|";		// generate a regex with each word in search query
	});
	regString = regString.substring(0, regString.length - 1);	// get rid of trailing |
	
	let regex = new RegExp(regString, "gi");
	for (const key in shelters) {
		if (shelters.hasOwnProperty(key)) {
			let shelter = shelters[key];

			if (key.match(regex)) {
				filtered[key] = shelter;
			}
		}
	}

	return filtered;
}

function searchByGender(shelters, gender) {
	let filtered = {};

	for (const key in shelters) {
		if (shelters.hasOwnProperty(key)) {
			let shelter = shelters[key];
			let restrictions = shelter.Restrictions;
			let maleRegex = /\bmale\b|\bmen\b/gi;
			let femaleRegex = /\bfemale\b|\bwomen\b/gi;
			if (gender === "male") {
				if (restrictions.match(maleRegex)) {
					filtered[key] = shelter;
				} else if (!restrictions.match(femaleRegex)) {
					filtered[key] = shelter;
				}
			} else if (gender === "female") {
				if (restrictions.match(femaleRegex)) {
					filtered[key] = shelter;
				} else if (!restrictions.match(maleRegex)) {
					filtered[key] = shelter;
				}
			} else {
				filtered[key] = shelter;
			}
		}
	}

	return filtered;
}

function searchByAgeRange(shelters, ageRange) {
	let filtered = {};

	for (const key in shelters) {
		if (shelters.hasOwnProperty(key)) {
			let shelter = shelters[key];
			let restrictions = shelter.Restrictions;
			let familyMatch = restrictions.match(/families|family/gi);
			let childrenMatch = restrictions.match(/children/gi);
			let youngAdultsMatch = restrictions.match(/young adults/gi);
			let anyoneMatch = restrictions.match(/anyone/gi);

			switch (ageRange) {
				case "families":
					if (familyMatch) {
						filtered[key] = shelter;
					} else if (anyoneMatch) {
						filtered[key] = shelter;
					}
					break;
				case "children":
					if (childrenMatch) {
						filtered[key] = shelter;
					} else if (anyoneMatch) {
						filtered[key] = shelter;
					}
					break;
				case "youngAdults":
					if (youngAdultsMatch) {
						filtered[key] = shelter;
					} else if (anyoneMatch) {
						filtered[key] = shelter;
					}
				case "anyone":
					if (anyoneMatch) {
						filtered[key] = shelter;
					}
					break;
				default:
					filtered[key] = shelter;
					break;
			}
		}
	}

	return filtered;
}

module.exports = (shelters, name=undefined, gender=undefined, ageRange=undefined) => {
	if (name) {
		if (name.length == 0) {
			return shelters;
		}
		return searchByName(shelters, name);
	} else if (gender) {
		return searchByGender(shelters, gender);
	} else if (ageRange) {
		return searchByAgeRange(shelters, ageRange);
	}

	return {};	// should never reach this
};
