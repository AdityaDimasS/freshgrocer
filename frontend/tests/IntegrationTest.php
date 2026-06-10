<?php

use PHPUnit\Framework\TestCase;

class IntegrationTest extends TestCase
{
    public function testOrderIntegration()
    {
        $url = 'http://localhost:8080/api/order';

        $payload = json_encode([
            'userId'    => 'U001',
            'productId' => 'P001',
            'tier'      => 'GOLD',
            'quantity'  => '5',
            'duration'  => '6',
            'isGroup'   => 'false',
            'address'   => 'Ponorogo'
        ]);

        $ch = curl_init($url);

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen($payload)
        ]);

        $response = curl_exec($ch);

        $httpCode = curl_getinfo(
            $ch,
            CURLINFO_HTTP_CODE
        );

        curl_close($ch);

        $result = json_decode(
            $response,
            true
        );

        $this->assertEquals(
            200,
            $httpCode
        );

        $this->assertEquals(
            'SUCCESS',
            $result['status']
        );

        $this->assertArrayHasKey(
            'total',
            $result
        );

        $this->assertArrayHasKey(
            'discount',
            $result
        );

        $this->assertArrayHasKey(
            'deliveryFee',
            $result
        );
    }
}