using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AtaqueDemonController : MonoBehaviour {

    public GameObject player;
	// Use this for initialization
	void Start () {
        
        Vector3 aleat = new Vector3(Random.Range(-0.8f, 1.3f), Random.Range(0.8f, -0.72f));
        Vector3 dir = transform.position - player.transform.position + aleat;
        if(dir.x > 3.5f || dir.y > 3.5f)
        {
            Destroy(this.gameObject);
        }
        else
        {
            Destroy(this.gameObject, 3);
        }
        GetComponent<Rigidbody>().AddForce(dir*(-1),ForceMode.Impulse);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter(Collider other)
    {

        {
            if (other.name == "MC")
            {
                other.GetComponent<Rigidbody>().AddForce(Vector3.up * 75);
                other.GetComponent<PlayerCont>().actualizarVidas(-1);
            }
        }


    }

}
