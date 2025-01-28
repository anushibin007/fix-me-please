# Population Data

## Resources/ Inputs

---

```typescript
interface PopulationNode {
	name: "string";
	value: {
		str: "string" | "";
		obj:
			| {
					[key: string]: PopulationNode;
			  }
			| {};
		arr: PopulationNode[] | [];
	};
}
```

#### JSON input

[Click here](https://raw.githubusercontent.com/anushibin007/fix-me-please/refs/heads/master/4-reactjs/json-input-1.json)

---

## Problem Statement :

Process the JSON and print the following output

1. If the value is a string, print it directly in {name}: {str} format
2. If the value is an array, iterate over the contents, add the index, print it in the below format.
    1. {name}: {str}
    2. {name} : {str}
3. If the value is an object, highlight the name print the keys and their values in the below format.
   {name}:
   {key}: {str}
   {key}: {str}

---

## Output expected for the above input

```
China : "The overall population is the 141 crores",

USA:
    "NY":
       1. "Manhattan": "The population of Manhattan is 1.6 lakhs",
       2. "Brooklyn": "The population of Brooklyn is 2.6 lakhs",
    "CA":
       "SF":
            "BA": "The population of Bay Area is 3.8 lakhs",
       "LA": "The population of Los Angeles is 38 lakhs",
    "TX": "The population of Texas is 3 crores",

India:

    1."Maharastra": "The population of Delhi is 1.6 crores",

    2. "Rajasthan":
        1. "Jodhpur": "The population of Jaipur is 3.6 lakhs",
        2. "Jaipur":
            1. "HawaMahal": "The population of HawaMahal is 1.2 lakhs",
            2. "Fort": "The population of Fort is 0.2 lakhs",
        3.  "Thar Desert":
              "Jaisalmer": "The population of Jaisalmer is 0.1 lakhs",

    3. "Telangana":
        "Hyderabad": "The population of Hyderabad is 1.6 lakhs",

Canada: "The population of Canada is 4 crores"
```

---
